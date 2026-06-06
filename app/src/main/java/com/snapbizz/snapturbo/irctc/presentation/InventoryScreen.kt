package com.snapbizz.snapturbo.irctc.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.snapbizz.snapturbo.irctc.data.remote.dto.MenuItemDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    viewModel: InventoryViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    var searchText by remember {
        mutableStateOf("")
    }

    val filteredItems =
        state.items.filter {
            it.title.contains(
                searchText,
                ignoreCase = true
            )
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Inventory")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFC2185B),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Text(
                text = "(${filteredItems.size} SKUS)",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            // Search Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )

                BasicTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp),
                    singleLine = true,
                    decorationBox = { innerTextField ->

                        if (searchText.isEmpty()) {
                            Text(
                                text = "Search Products",
                                color = Color.Gray
                            )
                        }

                        innerTextField()
                    }
                )

                Text(
                    text = "⊗",
                    color = Color.Gray
                )
            }

            HorizontalDivider()

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            LazyColumn {

                items(filteredItems) { item ->

                    InventoryItemCard(
                        item = item
                    )
                }
            }
        }
    }
}

@Composable
private fun InventoryItemCard(
    item: MenuItemDto
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 6.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F2F2)
        )
    ) {

        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded = !expanded
                    }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = item.img_url,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )

                Spacer(
                    modifier = Modifier.size(12.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = item.barcode
                    )

                    Text(
                        text = "MRP ₹${item.mrp / 100.0}"
                    )
                }

                Icon(
                    tint = Color(0xFF0077CC),
                    imageVector =
                        if (expanded)
                            Icons.Default.ArrowDropUp
                        else
                            Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }

            if (expanded) {

                HorizontalDivider()

                Column(
                    modifier = Modifier.padding(12.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        DetailItem(
                            "SP",
                            "${item.sell_price / 100.0}"
                        )

                        DetailItem(
                            "IN STOCK",
                            "0"
                        )

                        DetailItem(
                            "UNIT TYPE",
                            item.unit
                        )

                        DetailItem(
                            "HSN",
                            "-"
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    HorizontalDivider()

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        DetailItem(
                            "GST RATE",
                            item.gst
                        )

                        DetailItem(
                            "ROP",
                            "-"
                        )

                        DetailItem(
                            "MBQ",
                            "-"
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    HorizontalDivider()

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        DetailItem(
                            "CATEGORY",
                            item.category_name
                        )

                        DetailItem(
                            "SUB CATEGORY",
                            "Lunch"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.DetailItem(
    title: String,
    value: String
) {

    Column(
        modifier = Modifier
            .weight(1f, false)
            .padding(end = 8.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )

        Text(
            text = value,
            fontWeight = FontWeight.SemiBold
        )
    }
}
