package com.snapbizz.snapturbo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.commons.internet.NetworkStatus
import com.snapbizz.snapturbo.commons.internet.NetworkEntryPoint
import com.snapbizz.snapturbo.commons.language.LocalAppResources
import com.snapbizz.snapturbo.commons.utils.resources.AppResourcesImpl
import com.snapbizz.snapturbo.onboarding.login.presentation.viewmodel.LanguageViewModel
import com.snapbizz.snapturbo.onboarding.userlogin.presentation.ui.UserLoginScreen
import com.snapbizz.snapturbo.ui.components.snap_dialog.InternetDialog
import com.snapbizz.snapturbo.ui.navigation.AppNavGraph
import com.snapbizz.snapturbo.ui.root.AppRoot
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appDataStore: AppDataStore

    @Inject
    lateinit var appResources: AppResourcesImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // NetworkConnectivity via EntryPoint (non-Hilt)
        val networkConnectivity =
            EntryPointAccessors.fromApplication(
                this,
                NetworkEntryPoint::class.java
            ).networkConnectivity()

        setContent {
            AppRoot(
                appResources = appResources,
                appDataStore = appDataStore,
                networkConnectivity = networkConnectivity
            )
        }
    }
}

