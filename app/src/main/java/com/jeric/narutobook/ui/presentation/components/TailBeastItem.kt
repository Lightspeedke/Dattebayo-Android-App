package com.jeric.narutobook.ui.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jeric.narutobook.R
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel

@Preview
@Composable
fun TailedBeastPreview(){
    TailBeastItem(
        onClick = {},
        tailedBeastModel = TailedBeastModel(
            id = 1,
            images = listOf(""),
            jutsu = listOf(""),
            name = "Kurama",
            natureType = listOf(""),
            tools = listOf(""),
            uniqueTraits = listOf("")
        )
    )
}

fun TailedBeastModel.getShortDescription(): String {
    val jutsuInfo = jutsu?.joinToString(", ") ?: "an unknown set of jutsu techniques"
    val natureInfo = natureType?.joinToString(", ") ?: "an unidentified chakra nature"
    val traitsInfo = uniqueTraits?.joinToString(", ") ?: "no distinctive or notable traits"

    return "$name is a formidable tailed beast known to utilize $jutsuInfo. " +
            "It possesses chakra nature(s) such as $natureInfo, which contribute to its unique abilities. " +
            "Among its defining characteristics, it is recognized for $traitsInfo. " +
            "These elements combine to make $name one of the most remarkable creatures in the shinobi world."
}

@Composable
fun TailBeastItem(
    onClick: () -> Unit,
    tailedBeastModel: TailedBeastModel,
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
            .data(if (tailedBeastModel.images?.isNotEmpty() == true) tailedBeastModel.images.last() else R.drawable.onboarding_3)
            .placeholder(drawableResId = R.drawable.ic_placeholder)
            .error(drawableResId = R.drawable.onboarding_3)
            .crossfade(true)
            .build()

        val shortBio = remember(tailedBeastModel) {
            tailedBeastModel.getShortDescription()
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
            text = tailedBeastModel.name,
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