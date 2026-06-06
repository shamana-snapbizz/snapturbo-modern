package com.snapbizz.snapturbo.irctc.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import kotlinx.coroutines.launch

private val AxisPink = Color(0xFFB01257)

data class HomeItem(
    val title: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IrctcHomeScreen(
    onInventoryClick: () -> Unit
) {

    val homeItems = remember {
        listOf(
            HomeItem("Billing Cart", Icons.Default.ShoppingCart),
            HomeItem("Inventory", Icons.Default.Inventory2),
            HomeItem("Bills", Icons.Default.ReceiptLong),
            HomeItem("Customers", Icons.Default.People),
            HomeItem("Transactions", Icons.Default.SwapHoriz),
            HomeItem("Reports", Icons.Default.Assessment)
        )
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { homeItems.size }
    )

    val scope = rememberCoroutineScope()

    val currentPage = pagerState.currentPage

    val currentItem = homeItems[currentPage]

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "Digital Dukaan",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AxisPink
                )
            )
        },

        bottomBar = {

            NavigationBar(
                containerColor = Color.White
            ) {

                homeItems.forEachIndexed { index, item ->

                    NavigationBarItem(
                        selected = currentPage == index,

                        onClick = {

                            scope.launch {

                                pagerState.animateScrollToPage(index)

                                if (index == 1) {
                                    onInventoryClick()
                                }
                            }
                        },

                        icon = {

                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },

                        label = {

                            Text(
                                text = item.title,
                                fontSize = 10.sp
                            )
                        },

                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = AxisPink,
                            selectedTextColor = AxisPink,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    enabled = currentPage > 0,

                    onClick = {

                        scope.launch {

                            if (currentPage > 0) {
                                pagerState.animateScrollToPage(
                                    currentPage - 1
                                )
                            }
                        }
                    }
                ) {

                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = if (currentPage  > 0)
                            Color.Gray
                        else
                            Color.LightGray
                    )
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.width(180.dp)
                ) { page ->

                    val item = homeItems[page]

                    Card(
                        modifier = Modifier.size(
                            width = 180.dp,
                            height = 140.dp
                        ),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(
                            1.dp,
                            Color(0xFFD9D9D9)
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = AxisPink,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                }

                IconButton(
                    enabled = currentPage  < homeItems.lastIndex,

                    onClick = {

                        scope.launch {

                            if (currentPage < homeItems.lastIndex) {

                                pagerState.animateScrollToPage(
                                    currentPage + 1
                                )
                            }
                        }
                    }
                ) {

                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        tint = if (currentPage < homeItems.lastIndex)
                            Color.Gray
                        else
                            Color.LightGray
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = currentItem.title,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
