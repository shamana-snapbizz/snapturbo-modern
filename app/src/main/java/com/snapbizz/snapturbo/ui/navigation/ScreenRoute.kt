package com.snapbizz.snapturbo.ui.navigation


sealed class ScreenRoute(val route: String) {

    object Splash : ScreenRoute("splash")

    object Auth {
        const val graph = "auth_graph"

        object Login : ScreenRoute("auth/login")
        object Registration : ScreenRoute("auth/registration")
        object CustomerRegistration : ScreenRoute("auth/customer")
        object UserLogin : ScreenRoute("auth/user")
        object PosName : ScreenRoute("auth/pos_name")
        object EntryBalance : ScreenRoute("auth/entry_balance")
    }

    object Dashboard {
        const val graph = "dashboard_graph"

        object Billing : ScreenRoute("dashboard/billing")
        object Customer : ScreenRoute("dashboard/customer")
        object Inventory : ScreenRoute("dashboard/inventory")
        object Bills : ScreenRoute("dashboard/bills")
        object Reports : ScreenRoute("dashboard/reports")
    }
}
