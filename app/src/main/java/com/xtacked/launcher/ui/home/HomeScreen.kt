package com.xtacked.launcher.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.Text as TvText
import com.xtacked.launcher.AppContainer
import com.xtacked.launcher.R
import com.xtacked.launcher.ui.components.CarouselRow
import com.xtacked.launcher.ui.components.OfflineBanner
import com.xtacked.launcher.ui.theme.XtackedBg
import com.xtacked.launcher.ui.theme.XtackedPrimary
import com.xtacked.launcher.ui.theme.XtackedTextPrimary
import com.xtacked.launcher.ui.theme.XtackedTypography

@Composable
fun HomeScreen(
    container: AppContainer,
    onOpenDetails: (String) -> Unit,
    onOpenMyList: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(container))
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(XtackedBg)
    ) {
        HomeTopBar(
            onOpenMyList = onOpenMyList
        )
        Box(Modifier.padding(start = 48.dp, end = 48.dp, bottom = 4.dp)) {
            OfflineBanner(visible = !state.online)
        }

        when {
            state.loading && state.feed == null -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    TvText("Loading Xtacked…", color = XtackedTextPrimary)
                }
            }
            state.feed == null -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    TvText("No content available.", color = XtackedTextPrimary)
                }
            }
            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(top = 24.dp, bottom = 48.dp),
                    verticalArrangement = Arrangement.spacedBy(28.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.feed!!.carousels, key = { it.id }) { carousel ->
                        CarouselRow(
                            carousel = carousel,
                            onItemClick = { onOpenDetails(it.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeTopBar(onOpenMyList: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 48.dp, end = 48.dp, top = 22.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Spacer(Modifier.width(12.dp))
        TvText(
            text = "XTACKED",
            style = XtackedTypography.ScreenTitle.copy(fontWeight = FontWeight.Bold),
            color = XtackedTextPrimary
        )
        TvText(
            text = "  LAUNCHER",
            style = XtackedTypography.ScreenTitle.copy(fontWeight = FontWeight.Light, letterSpacing = 4.sp),
            color = XtackedPrimary
        )
        Spacer(Modifier.weight(1f))
        MyListButton(onClick = onOpenMyList)
    }
}

@Composable
private fun MyListButton(onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()
    val borderColor = if (focused) Color.White else Color.Transparent
    val bg = if (focused) Color(0xFF23232E) else Color(0xFF14141C)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(bg)
            .border(2.dp, borderColor, RoundedCornerShape(10.dp))
            .clickable(interactionSource = interactionSource, indication = null, onClick = onClick)
            .focusable(interactionSource = interactionSource)
            .padding(horizontal = 18.dp, vertical = 10.dp)
    ) {
        TvText(
            text = "My List",
            style = XtackedTypography.Button,
            color = XtackedTextPrimary
        )
    }
}
