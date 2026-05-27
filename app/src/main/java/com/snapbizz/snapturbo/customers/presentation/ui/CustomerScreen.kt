package com.snapbizz.snapturbo.customers.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.customers.domain.model.Customer
import com.snapbizz.snapturbo.customers.presentation.viewmodel.CustomersViewModel
import com.snapbizz.snapturbo.ui.components.loading.PosLoadingBar

@Composable
fun CustomersScreen(
    viewModel: CustomersViewModel = hiltViewModel()
) {
    val state = viewModel.customersState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCustomers()

    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            SnapCircularLoader(size = 48.dp)
    //            CssStyleLoader()
        PosLoadingBar()
    }
    when (val result = state.value) {
        is NetworkResult.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is NetworkResult.Success -> {
            CustomerList(customers = result.data)
        }
        is NetworkResult.Error -> {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text(text = "Error: ${result.message}")
            }
        }
    }
}

@Composable
fun CustomerList(customers: List<Customer>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(customers) { customer ->
            CustomerItem(customer)
        }
    }
}

@Composable
fun CustomerItem(customer: Customer) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = customer.name, style = MaterialTheme.typography.titleMedium)
            Text(text = customer.email, style = MaterialTheme.typography.bodyMedium)
        }
    }
}