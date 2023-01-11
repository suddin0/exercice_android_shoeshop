package dev.papilionem.showshop.screens.detailsScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.papilionem.showshop.data.Shoe
import dev.papilionem.showshop.data.ShoeDataSource
import dev.papilionem.showshop.data.ShoeType
import dev.papilionem.showshop.ui.theme.ShowShopTheme
import java.util.UUID

@Preview
@Composable
fun DetailsScreen(/*navController: NavController,*/ id : String = UUID.randomUUID().toString()) {
    var shoe : Shoe? = ShoeDataSource().getShoeById(UUID.fromString(id))
    if(shoe == null) {
        // Return to the initial page
//        return Text(text = "No shoe found using the ID : $id")
        shoe = ShoeDataSource().getDefaultShoe()
    }

//    val shoe : Shoe = ShoeDataSource().getDefaultShoe()

    ShowShopTheme {
        DetailsScreenBody(shoe = shoe)
    }

}

@Composable
fun DetailsScreenBody(shoe: Shoe) {

    var currentShoeType by remember {
        mutableStateOf(shoe.shoeTypes[0])
    }

    var currentShoeSize by remember {
        mutableStateOf(currentShoeType.sizeList[0])
    }

    var shoeQuantity by remember {
        mutableStateOf(1)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier,
            topBar = {TopAppBarDetails()}
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Image
                Card(
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally),
//                    backgroundColor = Color.Red,
                    elevation = 0.dp

                ) {
//                    Image(imageVector = Icons.Default.Favorite, contentDescription = "Product image")/**/
                    val fromId = currentShoeType.thumbnailImagePainter != null
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                        model = if(fromId) currentShoeType.thumbnailImagePainter else currentShoeType.thumbnailImage ,
                        contentDescription = "Product Image")

                }

                // Name of the product
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = shoe.name,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onBackground),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

                // Gender
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = if (shoe.isForMan) "Men" else "Women",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, color = MaterialTheme.colors.onBackground),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

                // star and score

                // sizes
                Spacer(modifier = Modifier.height(22.dp))
                Sizes(currentShoeType.sizeList, currentShoeSize) {newShoeSize ->
                    currentShoeSize = newShoeSize
                }

                // color
                Spacer(modifier = Modifier.height(22.dp))
                ShoeColors(shoe.shoeTypes, currentShoeType) {shoeType ->
                    currentShoeType = shoeType

                }

                // quantity of the purchasse and unit price
                Spacer(modifier = Modifier.height(22.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    // The counter element
                    ShowQuantitycounter(
                        shoeQuantity,
                        addShoe = {
                            if ((shoeQuantity + 1) <= currentShoeType.totalInStock) {
                                shoeQuantity += 1
                            }

                        },
                        removeShoe = {
                            if ((shoeQuantity - 1) > 0) {
                                shoeQuantity -= 1
                            }
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${currentShoeType.price}$",
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colors.onBackground)
                    )
                }
                
                // Total Price section
                Spacer(modifier = Modifier.weight(1f))
                TotalPriceAddToCart(currentShoeType, shoeQuantity) {
                    println("Added to cart")
                }

            }
        }
    }
}

@Composable
fun TotalPriceAddToCart(currentShoe : ShoeType, quantity : Int, onClickButton : () -> Unit) {
    Card(modifier = Modifier
        .shadow(elevation = 1.dp)
//        .border(1.dp, Color.Gray)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            // Toptal Price
            val textStyle = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = MaterialTheme.colors.onBackground)
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Total price", style = textStyle)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${currentShoe.price * quantity}$", style = textStyle)
            }
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),

                onClick = onClickButton,

                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = "Add to Cart",
                    style = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Normal))
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun ShowQuantitycounter(
    shoeQuantity : Int,
    addShoe : () -> Unit,
    removeShoe :  () -> Unit,
    disabled : Boolean = false
) {
    val interactionSource = MutableInteractionSource()

    Card(
        modifier = Modifier
//            .align(alignment = Alignment.CenterVertically)
            .height(40.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            ) {
            // Minus bytton
            Card(
                modifier = Modifier
                    .size(30.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { addShoe() },
                elevation = 0.dp,
                shape = RoundedCornerShape(10.dp),

            ) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = "Add icon")
            }

            // Value
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = shoeQuantity.toString(),
                style = TextStyle(fontSize = 20.sp, color = MaterialTheme.colors.onBackground)
            )

            // plus button
            Card(
                modifier = Modifier
                    .size(30.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { removeShoe() },
                elevation = 0.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .weight(1f, fill = false)
                        .fillMaxWidth(),
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add icon")
            }
        }
    }
}

@Composable
fun ShoeColors(
    ShoeTypes : ArrayList<ShoeType>,
    currentShoeType : ShoeType,
    onClick : (shoeType : ShoeType) -> Unit
) {
    val interactionSource = MutableInteractionSource()

    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Text(
            text = "Colors",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colors.onBackground),
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyRow() {
                items(ShoeTypes) { shoetype ->
                        Card(
                            modifier = Modifier
                                .border(
                                    if (shoetype == currentShoeType) 2.dp else 0.dp,
                                    MaterialTheme.colors.onSurface, shape = CircleShape
                                )
                                .size(40.dp)
                                .align(alignment = Alignment.CenterVertically)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) { onClick(shoetype) },

                        backgroundColor = MaterialTheme.colors.background,
                            shape = CircleShape,
                            elevation = 0.dp
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillParentMaxSize()
                                    .padding(5.dp)
                                    .border(1.dp, Color.LightGray, shape = CircleShape),
                                shape = CircleShape,
                                backgroundColor = shoetype.color,
                                elevation = 0.dp,
                                content = {}
                                )
                        }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}


@Composable
fun Sizes(sizes : ArrayList<Double>, currentSize : Double, onClick : (size : Double) -> Unit) {
    val interactionSource = MutableInteractionSource()

    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Text(
            text = "Size",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colors.onBackground),
//            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyRow() {
                items(sizes) { size ->
                    Card(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .padding(1.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { onClick(size) },
                        border = BorderStroke(if (currentSize == size) 0.dp else 1.dp , Color.Gray),
                        shape = RoundedCornerShape(10.dp),
                        backgroundColor = if (currentSize == size) MaterialTheme.colors.primary else MaterialTheme.colors.surface


                    ) {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = size.toString(),
                                style = TextStyle(fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (currentSize == size) Color.White else MaterialTheme.colors.onSurface
                                ),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}

@Composable
fun TopAppBarDetails(onClickBackButton : () -> Unit = {Log.d("Details Backbutton", "Clicked!")}) {
    TopAppBar(
        title = {
            Text(
                text = "Details",
                style = TextStyle(
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold))
        },
        actions = {
            Log.d("TAG", "DetailsScreen: ")
        },
        navigationIcon = { Icon(
            modifier = Modifier.clickable { onClickBackButton() },
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Icon",
        )},
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
}