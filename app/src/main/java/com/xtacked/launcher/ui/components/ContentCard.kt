package com.xtacked.launcher.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.Glow
import androidx.tv.material3.Scale
import androidx.tv.material3.Text as TvText
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage
import com.xtacked.launcher.data.model.ContentItem
import com.xtacked.launcher.ui.theme.XtackedFocusRing
import com.xtacked.launcher.ui.theme.XtackedGlow
import com.xtacked.launcher.ui.theme.XtackedTextPrimary
import com.xtacked.launcher.ui.theme.XtackedTextSecondary
import com.xtacked.launcher.ui.theme.XtackedTextTertiary
import com.xtacked.launcher.ui.theme.XtackedTypography

@Composable
fun ContentCard(
    item: ContentItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    cardWidth: Int = 180
) {
    val cardShape = RoundedCornerShape(12.dp)
    Card(
        onClick = onClick,
        modifier = modifier.width(cardWidth.dp),
        shape = CardDefaults.shape(shape = cardShape),
        colors = CardDefaults.colors(
            containerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        ),
        scale = Scale(scale = 1.08f, scaleDown = Scale.None),
        border = Border(
            border = androidx.compose.foundation.BorderStroke(2.dp, XtackedFocusRing),
            shape = cardShape,
            focusedBorder = androidx.compose.foundation.BorderStroke(2.dp, XtackedFocusRing)
        ),
        glow = Glow(
            glowColor = XtackedGlow,
            elevation = 8.dp
        )
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
                    .clip(cardShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                AsyncImage(
                    model = item.posterUrl,
                    contentDescription = "${item.title} poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height((cardWidth * 1.5f).dp)
                )
                // Bottom gradient so title overlay is readable in later polish
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color(0xCC000000))
                            )
                        )
                )
            }

            TvText(
                text = item.title,
                style = XtackedTypography.CardTitle,
                color = XtackedTextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MetaDotText(item.yearText)
                MetaDot()
                MetaDotText(item.type.label())
                if (item.rating > 0.0) {
                    MetaDot()
                    MetaDotText(item.ratingText)
                }
            }

            if (item.primaryGenre.isNotEmpty()) {
                TvText(
                    text = item.primaryGenre,
                    style = XtackedTypography.CardMeta,
                    color = XtackedTextTertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.height(2.dp))
        }
    }
}

@Composable
private fun MetaDotText(text: String) {
    TvText(text = text, style = XtackedTypography.CardMeta, color = XtackedTextSecondary)
}

@Composable
private fun MetaDot() {
    TvText(text = "·", style = XtackedTypography.CardMeta, color = XtackedTextTertiary)
}

private fun Modifier.padding(horizontal: Int = 0, vertical: Int = 0): Modifier =
    this.then(Modifier.padding(horizontal.dp, vertical.dp))
