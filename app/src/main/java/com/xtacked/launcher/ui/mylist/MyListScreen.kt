package com.xtacked.launcher.ui.mylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Text as TvText
import com.xtacked.launcher.AppContainer
import com.xtacked.launcher.data.model.ContentItem
import com.xtacked.launcher.ui.components.ContentCard
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

private const val COLUMNS = 5

@Composable
fun MyListScreen(
    container: AppContainer,
    onOpenDetails: (String) -> Unit,
    onBack: () -> Unit
) {
    val viewModel: MyListViewModel = viewModel(factory = MyListViewModelFactory(container))
    val items by viewModel.items.collectAsStateWithLifecycle()

    var showClearConfirm by remember { mutableStateOf(false) }
    val firstCardFocus = remember { FocusRequester() }

    Box(modifier = Modifier.fillMaxSize().background(XtackedBg)) {

        Column(modifier = Modifier.fillMaxSize()) {
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 48.dp, end = 48.dp, top = 22.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FocusableChip(label = "Back", onClick = onBack)
                Spacer(Modifier.width(12.dp))
                TvText(
                    text = "My List",
                    style = XtackedTypography.ScreenTitle.copy(fontWeight = FontWeight.Bold),
                    color = XtackedTextPrimary
                )
                if (items.isNotEmpty()) {
                    TvText(
                        text = "  ${items.size} title" + if (items.size == 1) "" else "s",
                        style = XtackedTypography.CardMeta,
                        color = XtackedTextTertiary
                    )
                }
                Spacer(Modifier.weight(1f))
                if (items.isNotEmpty()) {
                    FocusableChip(
                        label = "Clear All",
                        onClick = { showClearConfirm = true }
                    )
                }
            }

            if (items.isEmpty()) {
                EmptyState(onBack = onBack)
            } else {
                val rows = items.chunked(COLUMNS)
                TvLazyColumn(
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        start = 48.dp, end = 48.dp, top = 20.dp, bottom = 48.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(rows) { rowItems ->
                        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            rowItems.forEachIndexed { index, item ->
                                val isFirst = rowItems === rows.firstOrNull() && index == 0
                                MyListCard(
                                    item = item,
                                    onOpen = { onOpenDetails(item.id) },
                                    onRemove = { viewModel.remove(item.id) },
                                    focusRequester = if (isFirst) firstCardFocus else null
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showClearConfirm) {
            ConfirmDialog(
                title = "Clear My List?",
                message = "This will remove all saved titles from your list. This cannot be undone.",
                confirmLabel = "Clear All",
                onConfirm = {
                    viewModel.clearAll()
                    showClearConfirm = false
                },
                onDismiss = { showClearConfirm = false }
            )
        }
    }

    // Focus the first card if the list is non-empty
    LaunchedEffect(items.size) {
        if (items.isNotEmpty()) {
            runCatching { firstCardFocus.requestFocus() }
        }
    }
}

@Composable
private fun MyListCard(
    item: ContentItem,
    onOpen: () -> Unit,
    onRemove: () -> Unit,
    focusRequester: FocusRequester?
) {
    var cardFocused by remember { mutableStateOf(false) }
    val cardModifier = if (focusRequester != null) {
        Modifier.focusRequester(focusRequester)
    } else Modifier

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ContentCard(
            item = item,
            onClick = onOpen,
            modifier = cardModifier.onFocusChanged { cardFocused = it.isFocused }
        )
        RemoveChip(onClick = onRemove, emphasized = cardFocused)
    }
}

@Composable
private fun RemoveChip(onClick: () -> Unit, emphasized: Boolean) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()

    val bg = when {
        focused -> Color(0xFF7A0A10)
        emphasized -> XtackedSurfaceHigh
        else -> XtackedSurface
    }
    val borderColor = if (focused) Color.White else Color.Transparent
    val textColor = when {
        focused -> Color.White
        emphasized -> XtackedPrimary
        else -> XtackedTextSecondary
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bg)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .focusable(interactionSource = interactionSource)
            .padding(horizontal = 14.dp, vertical = 6.dp)
            .onFocusChanged { }
    ) {
        // border drawn as a second Box for simplicity
        TvText(
            text = "Remove",
            style = XtackedTypography.CardMeta,
            color = textColor
        )
    }
}

@Composable
private fun EmptyState(onBack: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TvText(
                text = "Your list is empty",
                style = XtackedTypography.ScreenTitle.copy(fontWeight = FontWeight.SemiBold),
                color = XtackedTextPrimary
            )
            Spacer(Modifier.height(10.dp))
            TvText(
                text = "Movies and series you save will appear here.",
                style = XtackedTypography.Body,
                color = XtackedTextSecondary
            )
            Spacer(Modifier.height(24.dp))
            FocusableChip(label = "Back to Home", onClick = onBack, prominent = true)
        }
    }
}

@Composable
private fun ConfirmDialog(
    title: String,
    message: String,
    confirmLabel: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    // Full-screen overlay (TV-friendly focus handling)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xCC000000))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onDismiss
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(XtackedSurfaceHigh)
                .padding(28.dp)
                .width(480.dp)
        ) {
            TvText(
                text = title,
                style = XtackedTypography.CarouselTitle,
                color = XtackedTextPrimary
            )
            Spacer(Modifier.height(12.dp))
            TvText(
                text = message,
                style = XtackedTypography.Body,
                color = XtackedTextSecondary
            )
            Spacer(Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(Modifier.weight(1f))
                FocusableChip(label = "Cancel", onClick = onDismiss)
                FocusableChip(label = confirmLabel, onClick = onConfirm, prominent = true)
            }
        }
    }
}
