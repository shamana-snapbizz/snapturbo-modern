package com.snapbizz.snapturbo.irctc.presentation

import com.snapbizz.snapturbo.irctc.data.remote.dto.MenuItemDto

data class InventoryUiState(
    val isLoading: Boolean = false,
    val items: List<MenuItemDto> = emptyList(),
    val error: String? = null
)
