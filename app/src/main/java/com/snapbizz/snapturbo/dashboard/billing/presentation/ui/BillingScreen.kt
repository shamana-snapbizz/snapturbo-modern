package com.snapbizz.snapturbo.dashboard.billing.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.snapbizz.snapturbo.R
import com.snapbizz.snapturbo.commons.language.LocalAppResources

@Composable
fun BillingScreen(onRegister : () -> Unit){
    Box(modifier = Modifier.fillMaxSize()
    ){
        val resource = LocalAppResources.current
        Text(text = resource.getString(R.string.billing_monitor), modifier = Modifier.align(Alignment.Center))
    }
    Button(onClick = onRegister) {
        Text("BillingScreen")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen2(){
    BillingScreen({})
}