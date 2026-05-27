package com.snapbizz.snapturbo.commons.network.authenticator

import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.onboarding.login.data.device.DeviceInfoProvider
import com.snapbizz.snapturbo.onboarding.login.data.device.DeviceSession
import com.snapbizz.snapturbo.onboarding.login.data.remote.TokenApiService
import com.snapbizz.snapturbo.onboarding.login.data.remote.model.GenerateTokenRequest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * An OkHttp Authenticator that handles automatic token refresh on 401 Unauthorized responses.
 *
 * This class breaks a potential dependency cycle by lazily injecting TokenApiService
 * using a Dagger Provider. It also uses a Mutex to prevent simultaneous token
 * refresh calls from multiple threads (a "thundering herd" problem).
 */
@Singleton
class TokenRefresher @Inject constructor(
    // Use Provider for lazy injection to avoid dependency cycles.
    private val tokenApiServiceProvider: Provider<TokenApiService>,
    private val devicSession: DeviceSession,
    private val appDataStore: AppDataStore
) : Authenticator {

    // A Mutex is a coroutine-safe lock to ensure only one refresh happens at a time.
    private val mutex = Mutex()

    /**
     * This method is triggered by OkHttp when a network call receives a 401 response.
     * It must run synchronously and return a new request, or null to fail.
     */
    override fun authenticate(route: Route?, response: Response): Request? {
        // Prevent infinite loops if we've already tried and failed twice.
        if (responseCount(response) >= 2) {
            Timber.w("Authentication failed twice. Giving up.")
            return null
        }

        // We must use runBlocking here as OkHttp's Authenticator is a synchronous API.
        // The internal logic uses a Mutex to be coroutine-safe and efficient.
        return runBlocking {
            // Read the token that was used for the failing request.
            val tokenUsedInFailedRequest = response.request.header("Authorization")?.substringAfter("Bearer ")

            // Lock with the Mutex to ensure only one refresh happens at a time.
            mutex.withLock {
                val currentTokenInStore = appDataStore.accessToken.first().orEmpty()

                // Double-check: If the token was already refreshed by another thread
                // while this thread was waiting for the lock, use the new token.
                if (tokenUsedInFailedRequest != null && tokenUsedInFailedRequest != currentTokenInStore) {
                    Timber.d("Token already refreshed. Retrying with new token.")
                    return@withLock newRequestWithToken(response.request, currentTokenInStore)
                }

                // Otherwise, we are the first thread to get the lock, so we must perform the refresh.
                Timber.d("Authentication required. Refreshing access token...")
                val newRefreshedToken = refreshToken()

                newRefreshedToken?.let {
                    Timber.d("Token refreshed successfully. Retrying request.")
                    newRequestWithToken(response.request, it)
                }
            }
        }
    }

    /**
     * Suspends to refresh the access token and saves it to the DataStore.
     * This function is fully asynchronous and efficient.
     */
     suspend fun refreshToken(): String? {
        return try {
            val tokenApiService = tokenApiServiceProvider.get()
            val request = devicSession.deviceId.let {
                devicSession.storeId.let { storeId ->
                    storeId?.let { it1 ->
                        GenerateTokenRequest(
                            // It's safer to read the latest values directly before the call
                            accessToken = appDataStore.accessToken.first().orEmpty(),
                            deviceId = it,
                            storeId = it1
                        )
                    }
                }
            }

            // Use Retrofit's suspend function directly.
            // Retrofit will throw an HttpException for non-2xx responses.
            val tokenResponse = request?.let { tokenApiService.generateToken(it) }
            val newAccessToken =   tokenResponse.takeIf {
                it!!.isSuccessful
            }
                ?.body()
                ?.token
                ?.takeIf { it.isNotBlank() }

            //   appDataStore.saveAuthToken(newAccessToken)
            //   newAccessToken
            // 5. Save the new token and return it.
            newAccessToken
                ?.takeIf { it.isNotBlank() }
                ?.let { token ->
                    appDataStore.saveAuthToken(token)
                }
            Timber.i("Successfully refreshed and saved new auth token.")
            return newAccessToken

        } catch (e: HttpException) {
            Timber.e(e, "Token refresh failed with HTTP status: ${e.code()}.")
            null
        } catch (e: IOException) {
            Timber.e(e, "Token refresh failed due to a network error.")
            null
        } catch (e: Exception) {
            Timber.e(e, "An unexpected error occurred during token refresh.")
            null
        }
    }

    /**
     * Creates a new request with the updated Authorization header.
     */
    private fun newRequestWithToken(request: Request, newAccessToken: String): Request {
        return request.newBuilder()
            .header("Authorization", "Bearer $newAccessToken")
            .build()
    }

    /**
     * A safeguard to count how many times the request has been retried.
     */
    private fun responseCount(response: Response): Int {
        var count = 1
        var prior = response.priorResponse
        while (prior != null) {
            count++
            prior = prior.priorResponse
        }
        return count
    }
}
