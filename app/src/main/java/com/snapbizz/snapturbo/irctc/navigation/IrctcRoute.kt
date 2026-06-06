package com.snapbizz.snapturbo.irctc.navigation

sealed class IrctcRoute(
    val route: String
) {
    data object Home : IrctcRoute("irctc_home")
    data object Inventory : IrctcRoute("irctc_inventory")
}
