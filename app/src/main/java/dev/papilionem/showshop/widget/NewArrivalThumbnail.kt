package dev.papilionem.showshop.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.papilionem.showshop.data.Shoe
import dev.papilionem.showshop.data.ShoeDataSource
import dev.papilionem.showshop.data.ShoeType

@Preview(showBackground = true)
@Composable
fun NewArrivalThumbnail(
    shoe : Shoe = ShoeDataSource().getDefaultShoe(),
    shoeType: ShoeType = ShoeDataSource().getDefaultShoe().shoeTypes[0],
    onButtonClick : () -> Unit = {}
) {
    val isLiked = false

    Card(
        modifier = Modifier
            .width(300.dp)
            .height(100.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = 1.dp,

    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*Image*/
            Card(modifier = Modifier
//                .fillMaxWidth()
                .height(100.dp)
                .width(100.dp),
                elevation = 0.dp
            ) {
                if(shoeType.thumbnailImagePainter != null) {
                    Image(
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = shoeType.thumbnailImagePainter!!),
                        contentDescription = "Thumbnail image",

                        )
                } else {
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                        model = shoeType.thumbnailImage,
                        contentDescription = "Thumbnail Image")
                }
            }

            /* Information */
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = shoe.brand,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
//                        color = Color(0xff3d3d4e),
                        color = MaterialTheme.colors.onBackground
                    )
                )
                Text(
                    text = if(shoe.isForMan) "man" else "women",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        color = Color(0xFF88888B)
                    )
                )
                Text(
                    text = "${shoeType.price.toString()}$",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF88888B)
                    )
                )

            }
            
            Spacer(modifier = Modifier.weight(1f, fill = false).fillMaxWidth())

            /*Add and Like*/
            Column() {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add icon")
                Spacer(modifier = Modifier.weight(1f))


                if(isLiked) {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = "Favorite icon",
                        tint = Color.Red)
                } else {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite icon")
                }
            }
        }
    }
}