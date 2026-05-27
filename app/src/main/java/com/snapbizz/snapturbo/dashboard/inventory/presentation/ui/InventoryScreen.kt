package com.snapbizz.snapturbo.dashboard.inventory.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.snapbizz.snapturbo.R
import com.snapbizz.snapturbo.commons.language.LocalAppResources

@Composable
fun InventoryScreen(){
    val resource = LocalAppResources.current

    Box(modifier = Modifier.fillMaxSize()
    ){

        Text(text = resource.getString(R.string.my_store), modifier = Modifier.align(Alignment.Center))
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewScreen4(){
    InventoryScreen()
}