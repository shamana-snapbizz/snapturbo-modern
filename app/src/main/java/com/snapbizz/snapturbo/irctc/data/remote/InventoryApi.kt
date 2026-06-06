package com.snapbizz.snapturbo.irctc.data.remote

import com.snapbizz.snapturbo.irctc.data.remote.dto.MenuResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface InventoryApi {

    @GET("v1/api/irctc/menu/")
    suspend fun getMenu(
        @Query("inwardTrainNumber")
        inwardTrainNumber: String
    ): MenuResponseDto
}
