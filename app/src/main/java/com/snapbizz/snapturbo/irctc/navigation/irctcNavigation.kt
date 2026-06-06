package com.snapbizz.snapturbo.irctc.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snapbizz.snapturbo.irctc.home.IrctcHomeScreen
import com.snapbizz.snapturbo.irctc.presentation.InventoryScreen

fun NavGraphBuilder.irctcNavigation(
    navController: NavController
) {

    composable(
        IrctcRoute.Home.route
    ) {
        IrctcHomeScreen(
            onInventoryClick = {
                navController.navigate(
                    IrctcRoute.Inventory.route
                )
            }
        )
    }

    composable(
        IrctcRoute.Inventory.route
    ) {
        InventoryScreen()
    }
}
