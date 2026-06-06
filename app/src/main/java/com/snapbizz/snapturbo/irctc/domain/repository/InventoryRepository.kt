package com.snapbizz.snapturbo.irctc.domain.repository

import com.snapbizz.snapturbo.irctc.data.remote.dto.MenuItemDto

interface InventoryRepository {

    suspend fun getMenu(): List<MenuItemDto>
}

