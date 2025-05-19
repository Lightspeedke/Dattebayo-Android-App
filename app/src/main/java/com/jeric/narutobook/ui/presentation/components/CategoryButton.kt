package com.jeric.narutobook.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.jeric.narutobook.ui.presentation.components.state.CategoryState
import com.jeric.narutobook.ui.theme.Black

@Composable
fun CategoryButton(
    categoryState: CategoryState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable{ onClick() }
            .background(
                Brush.linearGradient(
                    listOf(categoryState.startColor, categoryState.endColor)
                )
            )
            .padding(16.dp)
    ){
        Text(
            text = categoryState.title,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = modifier.weight(1f))
        Box(
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .scale(1f, .5f)
                    .offset(y = 40.dp)
                    .size(40.dp)
                    .background(
                        Brush.radialGradient(
                            listOf(
                                Black.copy(alpha = .3f),
                                Color.Transparent
                            ),
                        )
                    )
            )

            AsyncImageWithState(
                url = categoryState.iconUrl,
                contentDescription = categoryState.title,
                modifier = Modifier
                    .size(40.dp),
            )
        }
    }
}

@Composable
fun AsyncImageWithState(
    modifier: Modifier = Modifier,
    url: Int,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {

        val context = LocalContext.current
        val dataState by rememberUpdatedState(url)
        val request by remember {
            derivedStateOf {
                ImageRequest.Builder(context)
                    .data(dataState)
                    .crossfade(true)
                    .build()
            }
        }

        SubcomposeAsyncImage(
            contentScale = contentScale,
            model = request,
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = contentDescription,
        )
    }
}