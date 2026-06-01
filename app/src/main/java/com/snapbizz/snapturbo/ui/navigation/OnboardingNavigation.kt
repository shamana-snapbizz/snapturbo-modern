package com.snapbizz.snapturbo.ui.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.snapbizz.snapturbo.onboarding.login.presentation.ui.LoginScreen
import com.snapbizz.snapturbo.onboarding.login.presentation.viewmodel.LanguageViewModel
import com.snapbizz.snapturbo.onboarding.registration.presentation.ui.RegistrationScreen
import com.snapbizz.snapturbo.onboarding.registration.presentation.viewmodel.RegistrationViewModel
import com.snapbizz.snapturbo.onboarding.userlogin.presentation.ui.UserLoginRoute

fun NavGraphBuilder.onboaringNavGraph(
    navController: NavController,
    languageViewModel: LanguageViewModel,
) {
    navigation(
        route = ScreenRoute.Auth.graph,
        startDestination = ScreenRoute.Auth.Login.route
    ) {

        composable(ScreenRoute.Auth.Login.route) {
            LoginScreen(
                onAuthSuccess = { route ->
                    navController.navigate(ScreenRoute.Auth.Registration.route)
                }
            )

            /*LoginScreen (
                onAuthSuccess = { route ->
                    navController.navigate(ScreenRoute.Auth.Registration.route)
                }
            )*/
        }

        composable(ScreenRoute.Auth.Registration.route) {
            Log.e("OTP_DEBUG", "Navigating to Registration")
            RegistrationScreen(
                onDashBoardClick = {
                    navController.navigate(
                        ScreenRoute.Auth.UserLogin.route
                    )
                }
            )
        }

        composable(
            ScreenRoute.Auth.UserLogin.route
        ) {

            UserLoginRoute(
                onSuccess = {
                    navController.navigate(
                        ScreenRoute.Dashboard.Billing.route
                    )
                }
            )
        }

        /*composable(ScreenRoute.Auth.Registration.route) {
            CustomersScreen(

            )
        }*/
        //User Login Screen Admin and Password
        //POS Test Name
        //Opening Balance Screen
    }
}
