package com.xtacked.launcher.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text as TvText
import com.xtacked.launcher.ui.theme.XtackedSurfaceHigh
import com.xtacked.launcher.ui.theme.XtackedTextPrimary
import com.xtacked.launcher.ui.theme.XtackedTextTertiary
import com.xtacked.launcher.ui.theme.XtackedTypography

/**
 * Small notice shown only when the device is offline.
 * Content and My List stay fully usable; this is a courtesy indicator.
 */
@Composable
fun OfflineBanner(
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(XtackedSurfaceHigh)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF59E0B))
            )
            TvText(
                text = "Offline - using cached content",
                style = XtackedTypography.CardMeta,
                color = XtackedTextPrimary
            )
            TvText(
                text = "  My List still works",
                style = XtackedTypography.CardMeta,
                color = XtackedTextTertiary
            )
        }
    }
}
