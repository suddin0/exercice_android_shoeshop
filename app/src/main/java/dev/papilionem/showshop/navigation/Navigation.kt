package dev.papilionem.showshop.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.papilionem.showshop.navigation.Screen
import dev.papilionem.showshop.screens.detailsScreen.DetailsScreen
import dev.papilionem.showshop.screens.mainScreen.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(route =  Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(
            route =  Screen.DetailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {entry ->
//            val id = entry.arguments?.getString("id", "---")
//            println("Inside navigation : $id")

            entry.arguments?.getString("id")?.let {
                DetailsScreen(/*navController,*/ id = it)
            }
        }
    }
}