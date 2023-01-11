package dev.papilionem.showshop.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryColor = Color(0xFF666ADD)

private val DarkColorPalette = darkColors(
//    primary = Purple200,
    primary = primaryColor,
    primaryVariant = Purple700,
    secondary = Teal200,
//    background = DarkBG,
//    surface = DarkFG,
//    onBackground = DarkFG
    )

private val LightColorPalette = lightColors(
//    primary = Purple500,
    primary = Purple700,
    primaryVariant = Purple700,
    secondary = Teal200,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
//    background = LightBG,
//    onBackground = LightFG,
)

@Composable
fun ShowShopTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}