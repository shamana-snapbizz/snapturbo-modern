package com.snapbizz.snapturbo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.snapbizz.snapturbo.onboarding.login.presentation.viewmodel.LanguageViewModel
import com.snapbizz.snapturbo.onboarding.login.presentation.viewmodel.LoginViewModel
import com.snapbizz.snapturbo.onboarding.registration.presentation.viewmodel.RegistrationViewModel

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
    }
}
