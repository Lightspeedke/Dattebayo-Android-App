package com.jeric.narutobook.ui.presentation.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jeric.narutobook.R
import com.jeric.narutobook.domain.model.character.CharacterModel

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    image: CharacterModel?,
) {
    val imageUrl = remember {
        if (image?.images?.isNotEmpty() == true) image.images.last() else R.drawable.onboarding_3
    }

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build()
    val aspectRatio = image?.idToAspectRatio() ?: 1f

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
            .then(modifier)
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}


fun CharacterModel.idToAspectRatio(): Float {
    val numericId = id.toIntOrNull() ?: return 1f

    val normalized = (numericId % 50) / 100f
    return (0.5f + normalized).coerceIn(0.5f, 1f)
}