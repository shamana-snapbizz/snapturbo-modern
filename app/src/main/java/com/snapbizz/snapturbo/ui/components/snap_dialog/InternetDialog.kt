package com.snapbizz.snapturbo.ui.components.snap_dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.snapbizz.snapturbo.BuildConfig
import com.snapbizz.snapturbo.R
import com.snapbizz.snapturbo.commons.utils.resources.AppResources


@Composable
fun InternetDialog(
//    viewModel: CustomersViewModel = hiltViewModel(),
    deviceId: String?,
    resources: AppResources
) {
//    val deviceId by viewModel.deviceId.collectAsStateWithLifecycle()
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = buildString {
                    append(resources.getString(R.string.no_internet))
                    if (deviceId != null) {
                        append("\nDevice ID: ")
                        append(deviceId)
                        append("\n")
                        append(
                            BuildConfig.API_BASE_URL_V2 + BuildConfig.APPLICATION_ID
                        )
                     }
                }
            )
        },
        text = {
            Text(text = resources.getString(R.string.check_internet) +  BuildConfig.API_BASE_URL_V2 + BuildConfig.APPLICATION_ID)
        },
        confirmButton = {
            Button(onClick = { }) {
                Text(text = resources.getString(R.string.ok))
            }
        }
    )
}


