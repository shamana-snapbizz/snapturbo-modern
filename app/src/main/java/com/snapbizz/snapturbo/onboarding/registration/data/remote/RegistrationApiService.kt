package com.snapbizz.snapturbo.onboarding.registration.data.remote

import com.snapbizz.snapturbo.onboarding.registration.data.remote.model.RegistrationRequest
import com.snapbizz.snapturbo.onboarding.registration.data.remote.model.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RegistrationApiService {

@POST("{store_id}/edit_store")
suspend fun editStoreDetails(
    @Path("store_id") storeId: Long,

    @Query("device_id")
    deviceId: String,

    @Query("access_token")
    accessToken: String,

    @Query("store_id")
    queryStoreId: Long,

    @Body
    request: RegistrationRequest
): Response<RegistrationResponse>
}
