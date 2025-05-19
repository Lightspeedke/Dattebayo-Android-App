package com.jeric.narutobook.ui.presentation.akatsuki

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jeric.narutobook.R
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.ui.presentation.helper.LocalSafeArea

@Composable
fun AkatsukiScreen(akatsukiModel: AkatsukiViewModel, onBack: ()-> Unit,  onClickITem: (Int) -> Unit) {
    val akatsuki by akatsukiModel.akatsukiModel.collectAsStateWithLifecycle()
    AkatsukiContent(akatsukiModel = akatsuki, onBack = onBack, onClickITem = onClickITem)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AkatsukiContent(akatsukiModel: List<AkatsukiModel>, onBack: () -> Unit,  onClickITem: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Akatsuki",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 20.dp, bottom = 6.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBack() }, modifier = Modifier.testTag("akatsuki_back"),
                    ) {
                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        modifier = Modifier.padding(LocalSafeArea.current)
    ) { paddingValue ->
        Box(
            modifier = Modifier.padding(paddingValue)
        ) {
            BoxWithConstraints {
                val columns = when(maxWidth) {
                    in 0.dp..349.dp -> 1
                    in 350.dp..599.dp -> 2
                    in 600.dp..899.dp -> 3
                    in 900.dp..1199.dp -> 4
                    else -> 5
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(columns),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(20.dp),
                    modifier = Modifier
                        .testTag("akatsuki_col")
                ) {
                    items(items = akatsukiModel, key = {it.id}){ data ->
                        AkatsukiItem(
                            onClick = { onClickITem(data.id) },
                            akatsukiModel = data,
                            modifier = Modifier.testTag(data.id.toString())
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun AkatsukiItem(
    onClick: () -> Unit,
    akatsukiModel: AkatsukiModel,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .width(220.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            .padding(10.dp)
    ) {

        val context = LocalContext.current
        val imageRequest = ImageRequest.Builder(context)
            .data(if (akatsukiModel.images?.isNotEmpty() == true) akatsukiModel.images.last() else R.drawable.onboarding_3)
            .placeholder(drawableResId = R.drawable.ic_placeholder)
            .error(drawableResId = R.drawable.onboarding_3)
            .crossfade(true)
            .build()

        val shortBio = remember(akatsukiModel) {
            akatsukiModel.getShortDescription()
        }

        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .clip(MaterialTheme.shapes.medium)
        )

        Text(
            text = akatsukiModel.name,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall,
        )

        Text(
            text = shortBio,
            color = MaterialTheme.colorScheme.onBackground.copy(.8f),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

fun AkatsukiModel.getShortDescription(): String {
    val jutsuInfo = jutsu?.joinToString(", ") ?: "an unknown set of jutsu techniques"
    val natureInfo = natureType?.joinToString(", ") ?: "an unidentified chakra nature"
    val traitsInfo = tools?.joinToString(", ") ?: "no distinctive or notable traits"

    return "$name is a formidable tailed beast known to utilize $jutsuInfo. " +
            "It possesses chakra nature(s) such as $natureInfo, which contribute to its unique abilities. " +
            "Among its defining characteristics, it is recognized for $traitsInfo. " +
            "These elements combine to make $name one of the most remarkable creatures in the shinobi world."
}