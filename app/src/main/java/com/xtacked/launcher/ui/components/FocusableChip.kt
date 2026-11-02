package com.xtacked.launcher.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text as TvText
import com.xtacked.launcher.ui.theme.XtackedPrimary
import com.xtacked.launcher.ui.theme.XtackedSurface
import com.xtacked.launcher.ui.theme.XtackedSurfaceHigh
import com.xtacked.launcher.ui.theme.XtackedTextPrimary
import com.xtacked.launcher.ui.theme.XtackedTypography

@Composable
fun FocusableChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    prominent: Boolean = false,
    active: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()

    val bg: Color = when {
        focused -> XtackedSurfaceHigh
        prominent -> XtackedPrimary
        active -> XtackedSurfaceHigh
        else -> XtackedSurface
    }
    val borderColor = if (focused) Color.White else Color.Transparent

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(bg)
            .border(2.dp, borderColor, RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .focusable(interactionSource = interactionSource)
            .padding(horizontal = 18.dp, vertical = 10.dp)
    ) {
        TvText(
            text = label,
            style = XtackedTypography.Button,
            color = XtackedTextPrimary
        )
    }
}
