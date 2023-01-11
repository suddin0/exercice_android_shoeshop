package dev.papilionem.showshop.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

data class ShoeType(
    var color : Color,
    var thumbnailImage : String,
    var showcaseImageList : ArrayList<String>,
    var showcaseImagePainterList : ArrayList<Int>? = null,
    var price : Double,
    var sizeList : ArrayList<Double>,
    var totalInStock : Int = -1,
    var thumbnailImagePainter: Int? = null
)
