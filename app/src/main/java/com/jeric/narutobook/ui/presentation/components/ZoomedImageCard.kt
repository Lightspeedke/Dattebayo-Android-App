package com.jeric.narutobook.ui.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.memory.MemoryCache
import coil.request.ImageRequest
import com.jeric.narutobook.R
import com.jeric.narutobook.domain.model.character.CharacterModel
import com.skydoves.cloudy.Cloudy

@Composable
fun ZoomedImageCard(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    image: CharacterModel?
) {
    val imageUrl = remember {
        if (image?.images?.isNotEmpty() == true) image.images.last() else R.drawable.onboarding_3
    }
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(if (image?.images?.isNotEmpty() == true) image.images.last() else R.drawable.onboarding_3)
        .crossfade(true)
        .placeholderMemoryCacheKey(MemoryCache.Key(imageUrl.toString()))
        .build()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isVisible) {
            Cloudy(modifier = Modifier.fillMaxSize(), radius = 25) {}
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Card(modifier = modifier) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(CircleShape)
                            .size(25.dp),
                        model = imageRequest,
                        contentDescription = null
                    )
                    Text(
                        text = image?.name ?: "Anonymous",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = imageRequest,
                    contentDescription = null
                )
            }
        }
    }
}