package com.snapbizz.snapturbo.onboarding.downloadsync.data.remote

import com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model.DownloadSyncResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DownloadSyncApiService {

    @GET("{storeId}/ldb_downsync")
    suspend fun getDownloadFiles(

        @Path("storeId")
        storeId: Long,

        @Header("Authorization")
        jwtToken: String
    ): Response<DownloadSyncResponse>
}