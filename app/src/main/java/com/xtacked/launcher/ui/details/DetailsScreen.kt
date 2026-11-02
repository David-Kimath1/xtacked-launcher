package com.xtacked.launcher.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.Text as TvText
import coil.compose.AsyncImage
import com.xtacked.launcher.AppContainer
import com.xtacked.launcher.data.model.ContentItem
import com.xtacked.launcher.ui.components.FocusableChip
import com.xtacked.launcher.ui.theme.XtackedBg
import com.xtacked.launcher.ui.theme.XtackedPrimary
import com.xtacked.launcher.ui.theme.XtackedSurface
import com.xtacked.launcher.ui.theme.XtackedSurfaceHigh
import com.xtacked.launcher.ui.theme.XtackedTextPrimary
import com.xtacked.launcher.ui.theme.XtackedTextSecondary
import com.xtacked.launcher.ui.theme.XtackedTextTertiary
import com.xtacked.launcher.ui.theme.XtackedTypography
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DetailsScreen(
    container: AppContainer,
    contentId: String,
    onBack: () -> Unit
) {
    val viewModel: DetailsViewModel = viewModel(
        factory = DetailsViewModelFactory(container, contentId)
    )
    val state by viewModel.state.collectAsStateWithLifecycle()

    val primaryActionFocus = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(XtackedBg)
    ) {
        val content = state.content

        // Cinematic backdrop with dark gradient overlay
        if (content != null) {
            AsyncImage(
                model = content.backdropUrl,
                contentDescription = "${content.title} backdrop",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            XtackedBg.copy(alpha = 0.55f),
                            XtackedBg.copy(alpha = 0.92f),
                            XtackedBg
                        )
                    )
                )
        )

        // Content layer
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 48.dp, end = 48.dp, top = 24.dp, bottom = 48.dp)
        ) {
            // Top bar
            Row(verticalAlignment = Alignment.CenterVertically) {
                FocusableChip(label = "Back", onClick = onBack)
            }

            Spacer(Modifier.height(24.dp))

            when {
                state.loading && content == null -> {
                    Box(Modifier.fillMaxWidth().height(400.dp), contentAlignment = Alignment.Center) {
                        TvText("Loading…", color = XtackedTextPrimary)
                    }
                }
                content == null -> {
                    Box(Modifier.fillMaxWidth().height(400.dp), contentAlignment = Alignment.Center) {
                        TvText("Content not found.", color = XtackedTextPrimary)
                    }
                }
                else -> {
                    DetailsContent(
                        content = content,
                        isSaved = state.isSaved,
                        onToggleMyList = viewModel::toggleMyList,
                        primaryActionFocus = primaryActionFocus
                    )
                }
            }
        }
    }

    // Auto-focus the primary action once content is available
    LaunchedEffect(state.content) {
        if (state.content != null) {
            runCatching { primaryActionFocus.requestFocus() }
        }
    }
}

@Composable
private fun DetailsContent(
    content: ContentItem,
    isSaved: Boolean,
    onToggleMyList: () -> Unit,
    primaryActionFocus: FocusRequester
) {
    Row(verticalAlignment = Alignment.Top) {
        // Poster
        AsyncImage(
            model = content.posterUrl,
            contentDescription = "${content.title} poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(240.dp)
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(14.dp))
                .background(XtackedSurfaceHigh)
        )

        Spacer(Modifier.width(32.dp))

        // Info column
        Column(modifier = Modifier.weight(1f)) {
            TvText(
                text = content.title,
                style = XtackedTypography.ScreenTitle.copy(
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = XtackedTextPrimary
            )

            Spacer(Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MetaText(content.yearText)
                Dot()
                MetaText(content.type.label())
                Dot()
                MetaText(content.genreText)
                if (content.rating > 0.0) {
                    Dot()
                    MetaText(content.ratingText)
                }
                if (content.runtimeText.isNotEmpty()) {
                    Dot()
                    MetaText(content.runtimeText)
                }
            }

            Spacer(Modifier.height(8.dp))

            TvText(
                text = "Released ${content.releaseDate}",
                style = XtackedTypography.CardMeta,
                color = XtackedTextTertiary
            )

            Spacer(Modifier.height(18.dp))

            TvText(
                text = content.synopsis,
                style = XtackedTypography.Body,
                color = XtackedTextSecondary
            )

            Spacer(Modifier.height(24.dp))

            // Primary action
            FocusableChip(
                label = if (isSaved) "Remove from My List" else "Add to My List",
                onClick = onToggleMyList,
                prominent = !isSaved,
                active = isSaved,
                modifier = Modifier.focusRequester(primaryActionFocus)
            )

            if (content.viewingOptions.isNotEmpty()) {
                Spacer(Modifier.height(22.dp))
                TvText(
                    text = "Available on",
                    style = XtackedTypography.CardMeta,
                    color = XtackedTextTertiary
                )
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    content.viewingOptions.forEach { option ->
                        ViewingOptionChip(option)
                    }
                }
            }
        }
    }
}

@Composable
private fun MetaText(text: String) {
    TvText(text = text, style = XtackedTypography.CardMeta, color = XtackedTextSecondary)
}

@Composable
private fun Dot() {
    TvText(text = "·", style = XtackedTypography.CardMeta, color = XtackedTextTertiary)
}

@Composable
private fun ViewingOptionChip(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(XtackedSurface)
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        TvText(
            text = label,
            style = XtackedTypography.CardMeta,
            color = XtackedPrimary
        )
    }
}
