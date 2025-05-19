package com.jeric.narutobook.ui.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.jeric.narutobook.domain.model.character.CharacterModel

@Composable
fun ImagesVerticalGrid(
    modifier: Modifier = Modifier,
    images: LazyPagingItems<CharacterModel>,
    onImageClick: (String) -> Unit,
    onImageDragStart: (CharacterModel?) -> Unit,
    onImageDragEnd: () -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier.testTag("home_col"),
        columns = StaggeredGridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(10.dp),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(count = images.itemCount, key = {it}) { index ->
            val image = images[index]
            ImageCard(
                image = image,
                modifier = Modifier
                    .testTag(image?.id.toString())
                    .clickable { image?.id?.let { onImageClick(it) } }
                    .pointerInput(Unit) {
                        detectDragGesturesAfterLongPress(
                            onDragStart = { onImageDragStart(image) },
                            onDragCancel = { onImageDragEnd() },
                            onDragEnd = { onImageDragEnd() },
                            onDrag = { _, _ -> }
                        )
                    },
            )
        }
    }
}