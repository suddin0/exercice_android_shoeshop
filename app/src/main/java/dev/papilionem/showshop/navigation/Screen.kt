package dev.papilionem.showshop.navigation

sealed class Screen(val route : String) {
    object  MainScreen : Screen("main_screen")
    object  DetailsScreen : Screen("details_screen")

    fun withArgs(vararg args: String) : String {
        val url = buildString {
            append(route)

            args.forEach { arg ->
                append("/$arg")
            }
        }

//        println("Inside the string builder ->  $url")
        return url
    }
}