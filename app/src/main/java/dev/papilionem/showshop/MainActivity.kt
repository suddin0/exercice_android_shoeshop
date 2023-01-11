package dev.papilionem.showshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.papilionem.showshop.ui.theme.ShowShopTheme
import dev.papilionem.showshop.utils.Navigation



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowShopTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = MaterialTheme.colors.background)
                systemUiController.setNavigationBarColor(color = MaterialTheme.colors.background)
            }

//            MainScreen()
            Navigation()
        }
    }
}




//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    MainScreen()
}