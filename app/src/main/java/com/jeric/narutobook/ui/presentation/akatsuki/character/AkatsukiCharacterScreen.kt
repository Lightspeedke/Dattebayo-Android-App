package com.jeric.narutobook.ui.presentation.akatsuki.character

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jeric.narutobook.R
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.ui.presentation.akatsuki.getShortDescription
import com.jeric.narutobook.ui.presentation.components.InfoBox
import com.jeric.narutobook.ui.presentation.tailedbeast.OrderListRow
import com.jeric.narutobook.ui.presentation.tailedbeast.currentSheetFraction
import com.jeric.narutobook.ui.presentation.tailedbeast.getRandomBirthMonth
import com.jeric.narutobook.ui.theme.EXPANDED_RADIUS_LEVEL
import com.jeric.narutobook.ui.theme.EXTRA_LARGE_PADDING
import com.jeric.narutobook.ui.theme.INFO_ICON_SIZE
import com.jeric.narutobook.ui.theme.LARGE_PADDING
import com.jeric.narutobook.ui.theme.MEDIUM_PADDING
import com.jeric.narutobook.ui.theme.MIN_SHEET_HEIGHT
import com.jeric.narutobook.ui.theme.SMALL_PADDING
import com.jeric.narutobook.ui.theme.titleColor
import com.jeric.narutobook.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT

@Composable
fun AkatsukiCharacterScreen(onBack: () -> Unit, akatsukiModel: AkatsukiModel?) {
    AkatsukiCharacterContent(
        onBack = { onBack() },
        akatsukiModel = akatsukiModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AkatsukiCharacterContent(onBack: () -> Unit, akatsukiModel: AkatsukiModel?) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f) EXTRA_LARGE_PADDING
        else EXPANDED_RADIUS_LEVEL,
        label = "Radius Animation"
    )
    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        sheetDragHandle = null,
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            if (akatsukiModel != null) {
                AkatsukiBottomSheetContent(
                    akatsukiModel = akatsukiModel
                )
            }
        },
        content = {
            if (akatsukiModel != null) {
                AkatsukiBackgroundContent(
                    akatsukiModel = akatsukiModel,
                    imageFraction = currentSheetFraction,
                    onCloseClicked = { onBack() }
                )
            }
        }
    )
}

@Composable
fun AkatsukiBackgroundContent(
    akatsukiModel: AkatsukiModel?,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onCloseClicked: () -> Unit
) {

    val imageUrl = remember {
        if (akatsukiModel?.images?.isNotEmpty() == true) akatsukiModel.images.last() else R.drawable.onboarding_3
    }
    val animatedImageSize by animateFloatAsState(
        targetValue = imageFraction,
        label = "Radius Animation"
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(
                    fraction = (animatedImageSize + MIN_BACKGROUND_IMAGE_HEIGHT)
                        .coerceAtMost(1.0f)
                )
                .align(Alignment.TopCenter),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = imageUrl)
                .error(drawableResId = R.drawable.ic_placeholder)
                .build(),
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon),
                    tint = Color.White
                )
            }
        }

    }
}

@Composable
fun AkatsukiBottomSheetContent(
    akatsukiModel: AkatsukiModel,
    infoBoxIconColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = titleColor
) {
    val shortBio = remember(akatsukiModel) {
        akatsukiModel.getShortDescription()
    }
    val power = remember(akatsukiModel) {
        calculatePower(akatsukiModel)
    }

    val rankings = remember(akatsukiModel) {
        getPowerScore(akatsukiModel)
    }

    val birthMonth = remember {
        getRandomBirthMonth()
    }

    Column (
        modifier = Modifier
            .padding(all = LARGE_PADDING)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING)
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(bottom = LARGE_PADDING),
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null,
                tint = contentColor
            )
            Text(
                text = akatsukiModel.name,
                modifier = Modifier
                    .weight(8f),
                color = contentColor,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor = infoBoxIconColor,
                bigText = power.toString(),
                smallText = stringResource(R.string.power),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.power),
                iconColor = infoBoxIconColor,
                bigText = rankings.toString(),
                smallText = stringResource(R.string.rankings),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                bigText = birthMonth,
                smallText = stringResource(R.string.birthday),
                textColor = contentColor
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(0.5f)
                .padding(bottom = MEDIUM_PADDING),
            text = shortBio,
            color = contentColor,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            maxLines = 7
        )
        OrderListRow(
            contentColor = contentColor,
            type = akatsukiModel.natureType,
            abilities = akatsukiModel.jutsu
        )
    }


}

fun getPowerScore(member: AkatsukiModel): Int {
    val jutsuCount = member.jutsu?.size ?: 0
    val natureTypeCount = member.natureType?.size ?: 0
    val toolsCount = member.tools?.size ?: 0

    return jutsuCount + natureTypeCount + toolsCount
}

fun calculatePower(beast: AkatsukiModel): Int {
    val jutsuCount = beast.jutsu?.size ?: 0
    val natureCount = beast.natureType?.size ?: 0
    val toolsCount = beast.tools?.size ?: 0
    val traitsCount = beast.natureType?.size ?: 0

    val rawPower = (jutsuCount * 10) +
            (natureCount * 15) +
            (toolsCount * 5) +
            (traitsCount * 20)

    return rawPower.coerceIn(60, 100)
}







