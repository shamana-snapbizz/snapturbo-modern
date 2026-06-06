package com.snapbizz.snapturbo.irctc.data.repository

import com.snapbizz.snapturbo.irctc.data.remote.InventoryApi
import com.snapbizz.snapturbo.irctc.data.remote.dto.MenuItemDto
import com.snapbizz.snapturbo.irctc.domain.repository.InventoryRepository
import javax.inject.Inject

class InventoryRepositoryImpl @Inject constructor(
    private val api: InventoryApi
) : InventoryRepository {

    override suspend fun getMenu(): List<MenuItemDto> {
        return api.getMenu(
            inwardTrainNumber = "12345-t1"
        ).data
    }
}
