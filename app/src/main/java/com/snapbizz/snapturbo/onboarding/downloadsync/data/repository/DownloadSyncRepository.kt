package com.snapbizz.snapturbo.onboarding.downloadsync.data.repository

import android.content.Context
import android.util.Log
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.CustomerDao
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.InventoryDao
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.ProductDao
import com.snapbizz.snapturbo.onboarding.downloadsync.data.mapper.toEntity
import com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.DownloadSyncApiService
import com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model.ApiCustomerDto
import com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model.ApiInventoryDto
import com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model.ApiProductDto
import com.snapbizz.snapturbo.onboarding.downloadsync.data.sync.FileDownloader
import com.snapbizz.snapturbo.onboarding.downloadsync.data.sync.ZipUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model.DownloadSyncResponse
import com.snapbizz.snapturbo.onboarding.login.data.local.dao.UserDao
import com.snapbizz.snapturbo.onboarding.login.data.local.entity.UserEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class DownloadSyncRepository @Inject constructor(

    private val api: DownloadSyncApiService,

    private val appDataStore: AppDataStore,

    private val productDao: ProductDao,

    private val customerDao: CustomerDao,

    private val inventoryDao: InventoryDao,

    private val userDao: UserDao,

    private val fileDownloader: FileDownloader,

    @ApplicationContext
    private val context: Context
){
    suspend fun performDownloadSync(): Result<Unit> {

        return try {

            val storeId = appDataStore.storeId.first()

            val authToken = appDataStore.authToken.first()
                ?: return Result.failure(Exception("Auth token missing"))

            Log.e(
                "SHAMA_DSYNC",
                "storeId=$storeId"
            )
            Log.e(
                "SHAMA_DSYNC",
                "JWT=${appDataStore.authToken.first()}"
            )
            Log.e(
                "SHAMA_DSYNC",
                "ACCESS=${appDataStore.accessToken.first()}"
            )
            val response =
                api.getDownloadFiles(
                    storeId = storeId,
                    jwtToken = "Bearer $authToken"
                )
            Log.e(
                "SHAMA_DSYNC",
                "body=${response.errorBody()?.string()}"
            )
            Log.e(
                "DSYNC",
                "successBody=${response.body()}"
            )

            val body =
                response.body()
                    ?: return Result.failure(
                        Exception("Empty response")
                    )

            syncProducts(body)

            syncCustomers(body)

            syncInventory(body)

            insertAdminUser()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    private suspend fun syncProducts(
        response: DownloadSyncResponse
    ) {

        val file =
            response.data.products?.firstOrNull()
                ?: return
        Log.e(
            "SHAMA_DSYNC",
            "Products URL=${file.fileName}"
        )
        val parsed =
            parseZip<ApiProductDto>(
                file.fileName,
                "products.zip"
            )
        Log.e(
            "SHAMA_DSYNC",
            "Products Parsed=${parsed.size}"
        )

        productDao.replaceAll(
            parsed.map { it.toEntity() }
        )
        Log.e(
            "SHAMA_DSYNC",
            "Products Saved"
        )
    }

    private suspend fun syncCustomers(
        response: DownloadSyncResponse
    ) {

        val file =
            response.data.customers?.firstOrNull()
                ?: return

        val parsed =
            parseZip<ApiCustomerDto>(
                file.fileName,
                "customers.zip"
            )

        customerDao.replaceAll(
            parsed.map { it.toEntity() }
        )
    }

    private suspend fun syncInventory(
        response: DownloadSyncResponse
    ) {

        val file =
            response.data.inventories?.firstOrNull()
                ?: return

        val parsed =
            parseZip<ApiInventoryDto>(
                file.fileName,
                "inventory.zip"
            )

        inventoryDao.replaceAll(
            parsed.map { it.toEntity() }
        )
    }


    private suspend inline fun <reified T> parseZip(
        url: String,
        zipName: String
    ): List<T> = withContext(Dispatchers.IO) {

        val syncDir =
            File(context.cacheDir, "download_sync")
                .apply { mkdirs() }

        val zipFile =
            File(syncDir, zipName)

        Log.e("DSYNC", "Downloading $url")

        fileDownloader.downloadFile(
            url,
            zipFile
        )

        val extracted =
            ZipUtils.unzip(
                zipFile,
                syncDir
            )

        Log.e(
            "DSYNC",
            "Extracted Files = ${extracted.map { it.name }}"
        )

        val jsonFile =
            extracted.first()

        val json =
            jsonFile.readText()

        val type =
            object : TypeToken<List<T>>() {}.type

        Gson().fromJson<List<T>>(
            json,
            type
        )
    }

    suspend fun getProducts() =
        productDao.getProducts()

    suspend fun getProductCount() =
        productDao.getCount()

    suspend fun getCustomers() =
        customerDao.getCustomers()

    suspend fun getCustomerCount() =
        customerDao.getCustomerCount()

    private suspend fun insertAdminUser() {

        val existing =
            userDao.getAdmin()

        if (existing != null) return

        val now =
            System.currentTimeMillis()

        userDao.insert(
            UserEntity(
                username = "admin",
                password = "admin",
                name = "admin",
                roleId = 2,
                isDisabled = false,
                createdAt = now,
                updatedAt = now
            )
        )
    }
}
