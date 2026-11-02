package com.xtacked.launcher.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object XtackedTypography {
    val ScreenTitle = TextStyle(
        fontSize = 34.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.5.sp
    )
    val CarouselTitle = TextStyle(
        fontSize = 22.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.3.sp
    )
    val CardTitle = TextStyle(
        fontSize = 15.sp, fontWeight = FontWeight.Medium
    )
    val CardMeta = TextStyle(
        fontSize = 12.sp, fontWeight = FontWeight.Normal
    )
    val Body = TextStyle(
        fontSize = 15.sp, fontWeight = FontWeight.Normal
    )
    val Button = TextStyle(
        fontSize = 15.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.3.sp
    )
}
