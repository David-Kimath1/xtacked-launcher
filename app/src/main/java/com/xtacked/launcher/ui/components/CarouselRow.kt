package com.xtacked.launcher.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Text as TvText
import com.xtacked.launcher.data.model.ContentCarousel
import com.xtacked.launcher.data.model.ContentItem
import com.xtacked.launcher.ui.theme.XtackedTextPrimary
import com.xtacked.launcher.ui.theme.XtackedTypography

@Composable
fun CarouselRow(
    carousel: ContentCarousel,
    onItemClick: (ContentItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TvText(
            text = carousel.title,
            style = XtackedTypography.CarouselTitle,
            color = XtackedTextPrimary,
            modifier = Modifier.padding(start = 48.dp, bottom = 10.dp)
        )
        TvLazyRow(
            contentPadding = PaddingValues(horizontal = 48.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(carousel.items) { item ->
                ContentCard(item = item, onClick = { onItemClick(item) })
            }
        }
    }
}
