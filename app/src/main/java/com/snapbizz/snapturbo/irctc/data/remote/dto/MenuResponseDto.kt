package com.snapbizz.snapturbo.irctc.data.remote.dto

data class MenuResponseDto(
    val data: List<MenuItemDto>
)

data class MenuItemDto(
    val barcode: String,
    val title: String,
    val img_url: String,
    val unit: String,
    val mrp: Int,
    val sell_price: Int,
    val gst: String,
    val category_name: String,
    val category_id: Int
)
