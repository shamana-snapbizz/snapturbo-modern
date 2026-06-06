package com.snapbizz.snapturbo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.snapbizz.snapturbo.irctc.navigation.irctcNavigation
import com.snapbizz.snapturbo.onboarding.login.presentation.viewmodel.LanguageViewModel

@Composable
fun AppNavGraph(languageViewModel: LanguageViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Splash.route
    ) {

        splashRoute(navController)

        onboaringNavGraph(navController,languageViewModel)

        dashboardNavGraph(navController)

        irctcNavigation(navController)
    }
}
