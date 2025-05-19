package com.jeric.narutobook.ui.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jeric.narutobook.R
import com.jeric.narutobook.domain.model.character.CharacterModel
import com.jeric.narutobook.ui.presentation.components.EmptyScreen
import com.jeric.narutobook.ui.presentation.components.RatingWidget
import com.jeric.narutobook.ui.presentation.components.ShimmerEffect
import com.jeric.narutobook.ui.presentation.home.getShortBio
import com.jeric.narutobook.ui.theme.EXTRA_SMALL_PADDING
import com.jeric.narutobook.ui.theme.HERO_ITEM_HEIGHT
import com.jeric.narutobook.ui.theme.LARGE_PADDING
import com.jeric.narutobook.ui.theme.MEDIUM_PADDING
import com.jeric.narutobook.ui.theme.SMALL_PADDING

@Composable
fun ListContent(
    padding: PaddingValues,
    heroes: LazyPagingItems<CharacterModel>,
    onClick: (String) -> Unit,
) {
    val result = handlePagingResult(character = heroes)

    if (result){
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = padding),
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = heroes.itemSnapshotList.items,
                key = { hero ->
                    hero.id
                }
            ) { index ->
                CharacterItem(index, onClick = onClick)
            }
        }
    }
}

@Composable
fun CharacterItem(character: CharacterModel, onClick: (String) -> Unit) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(if (character.images?.isNotEmpty() == true) character.images.last() else R.drawable.ic_placeholder)
        .placeholder(drawableResId = R.drawable.ic_placeholder)
        .error(drawableResId = R.drawable.ic_placeholder)
        .crossfade(true)
        .build()

    val shortBio = remember(character) {
        character.getShortBio()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(character.id) }
            .height(HERO_ITEM_HEIGHT)
            .padding(horizontal = SMALL_PADDING),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(size = LARGE_PADDING),
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.45f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.5f),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = character.name,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White.copy(alpha = 0.5f),
                )
                Text(
                    text = shortBio,
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = EXTRA_SMALL_PADDING)
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = if (character.name.length > 3) 5.0 else 3.0
                    )

                    Text(
                        text = "(${if (character.name.length > 3) 5.0 else 3.0})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}


@Composable
fun handlePagingResult(character: LazyPagingItems<CharacterModel>) : Boolean {
    character.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }
        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error, heroes = character)
                false
            }
            character.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}
