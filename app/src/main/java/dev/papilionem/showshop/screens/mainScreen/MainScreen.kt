package dev.papilionem.showshop.screens.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.papilionem.showshop.component.Section
import dev.papilionem.showshop.component.TopBrands
import dev.papilionem.showshop.data.ShoeDataSource
import dev.papilionem.showshop.navigation.Screen
import dev.papilionem.showshop.ui.theme.ShowShopTheme
import dev.papilionem.showshop.widget.DefaultThumbnail
import dev.papilionem.showshop.widget.MainHeader
import dev.papilionem.showshop.widget.NewArrivalThumbnail

//@Preview
@Composable
fun MainScreen(navController: NavController) {
    val shoeList = ShoeDataSource().getShoeList()
    ShowShopTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                MainHeader()
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    /*Top Brand*/
                    TopBrands()

                    /*Top Brand*/
                    Spacer(modifier = Modifier.height(25.dp))
                    Section("Popular shoes") {
                        LazyRow(modifier = Modifier
                            .padding(start = 5.dp)
                            .fillMaxWidth()) {
                            items(shoeList) { shoe ->
                                Spacer(modifier = Modifier.width(5.dp))
                                DefaultThumbnail(shoe, shoe.shoeTypes[0]) {selectedShoe ->
                                    println(selectedShoe.id.toString())
//
                                    navController.navigate(
                                        Screen.DetailsScreen.withArgs(selectedShoe.id.toString())
//                                          Screen.DetailsScreen.withArgs("selectedShoe.id.toString()")
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                    }
                    /*New Arrivals*/
                    Spacer(modifier = Modifier.height(25.dp))
                    Section("New arrival") {
                        LazyRow(modifier = Modifier
                            .padding(start = 5.dp)
                            .fillMaxWidth()) {
                            items(shoeList) { shoe ->
                                Spacer(modifier = Modifier.width(5.dp))
                                NewArrivalThumbnail(shoe, shoe.shoeTypes[0])
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                    }
                    /*Trending now*/
                    Spacer(modifier = Modifier.height(25.dp))
                    Section("Trending now") {
                        LazyRow(modifier = Modifier
                            .padding(start = 5.dp)
                            .fillMaxWidth()) {
                            items(shoeList) { shoe ->
                                Spacer(modifier = Modifier.width(5.dp))
                                DefaultThumbnail(shoe, shoe.shoeTypes[0])
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                    }

                    // Bottom Spacer. this spacer should be present at the end of the column
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}