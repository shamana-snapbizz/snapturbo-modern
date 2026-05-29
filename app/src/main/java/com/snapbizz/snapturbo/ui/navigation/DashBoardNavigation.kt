package com.snapbizz.snapturbo.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.snapbizz.snapturbo.dashboard.billing.presentation.ui.BillingScreen
import com.snapbizz.snapturbo.dashboard.inventory.presentation.ui.InventoryScreen
import com.snapbizz.snapturbo.dashboard.report.presentation.ui.ReportsScreen


fun NavGraphBuilder.dashboardNavGraph(
    navController: NavController
) {
    navigation(
        route = ScreenRoute.Dashboard.graph,
        startDestination = ScreenRoute.Dashboard.Billing.route
    ) {
        composable(ScreenRoute.Dashboard.Billing.route) {
            BillingScreen(
//            onRegister = {
//                navController.navigate(ScreenRoute.Auth.Registration.route)
//            }
            )
        }

        composable(ScreenRoute.Dashboard.Inventory.route) {
            InventoryScreen()
        }

        composable(ScreenRoute.Dashboard.Reports.route) {
            ReportsScreen()
        }
    }
}
