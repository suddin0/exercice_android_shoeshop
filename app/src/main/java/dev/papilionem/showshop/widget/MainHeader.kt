package dev.papilionem.showshop.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.papilionem.showshop.R

@Preview
@Composable
fun MainHeader() {
    Surface() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {

                /*User Image*/
                Card(modifier = Modifier.size(45.dp), shape = CircleShape) {
                    val image = painterResource(id = R.drawable.profile_image)
                    Image(painter = image, contentDescription = "Profile image")
                }
                Spacer(modifier = Modifier.width(5.dp))
                // User Name
                Text(text = "Ameli Jack", style = TextStyle(fontSize = 16.sp))
            }

            Spacer(modifier = Modifier.weight(1f))

            Card(
                modifier = Modifier.size(30.dp),
                backgroundColor = Color.Transparent,
                elevation = 0.dp) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notification icon",
                    tint = Color.Gray)
            }
        }
    }
}

