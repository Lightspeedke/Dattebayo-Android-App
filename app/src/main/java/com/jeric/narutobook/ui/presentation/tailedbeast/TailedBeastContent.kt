package com.jeric.narutobook.ui.presentation.tailedbeast

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
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
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel
import com.jeric.narutobook.ui.presentation.components.InfoBox
import com.jeric.narutobook.ui.presentation.components.OrderedList
import com.jeric.narutobook.ui.presentation.components.getShortDescription
import com.jeric.narutobook.ui.theme.EXPANDED_RADIUS_LEVEL
import com.jeric.narutobook.ui.theme.EXTRA_LARGE_PADDING
import com.jeric.narutobook.ui.theme.INFO_ICON_SIZE
import com.jeric.narutobook.ui.theme.LARGE_PADDING
import com.jeric.narutobook.ui.theme.MEDIUM_PADDING
import com.jeric.narutobook.ui.theme.MIN_SHEET_HEIGHT
import com.jeric.narutobook.ui.theme.SMALL_PADDING
import com.jeric.narutobook.ui.theme.titleColor
import com.jeric.narutobook.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT


@ExperimentalMaterial3Api
@Composable
fun TailedBeastContent(
    onBack: () -> Unit,
    selectedTailedBeast: TailedBeastModel?,
) {
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
            if (selectedTailedBeast != null) {
                BottomSheetContent(
                    selectedHero = selectedTailedBeast,
                )
            }
        },
        content = {
            if (selectedTailedBeast != null) {
                BackgroundContent(
                    tailedBeastModel = selectedTailedBeast,
                    imageFraction = currentSheetFraction,
                    onCloseClicked = { onBack() }
                )
            }
        }
    )
}


@Composable
fun BackgroundContent(
    tailedBeastModel: TailedBeastModel,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onCloseClicked: () -> Unit
) {

    val imageUrl = remember {
        if (tailedBeastModel.images?.isNotEmpty() == true) tailedBeastModel.images.last() else R.drawable.onboarding_3
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


@OptIn(ExperimentalMaterial3Api::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == SheetValue.Hidden && targetValue == SheetValue.Hidden -> 1f
            currentValue == SheetValue.Expanded && targetValue == SheetValue.Expanded -> 0f
            currentValue == SheetValue.Hidden && targetValue == SheetValue.Expanded -> 1f
            currentValue == SheetValue.Expanded && targetValue == SheetValue.Hidden -> 0f
            else -> 1f
        }
    }


fun calculatePower(beast: TailedBeastModel): Int {
    val jutsuCount = beast.jutsu?.size ?: 0
    val natureCount = beast.natureType?.size ?: 0
    val toolsCount = beast.tools?.size ?: 0
    val traitsCount = beast.uniqueTraits?.size ?: 0

    val rawPower = (jutsuCount * 10) +
            (natureCount * 15) +
            (toolsCount * 5) +
            (traitsCount * 20)

    return rawPower.coerceIn(60, 100)
}

fun calculateBijudamaPower(beast: TailedBeastModel): Int {
    val jutsuCount = beast.jutsu?.size ?: 0
    val natureCount = beast.natureType?.size ?: 0
    val traitsCount = beast.uniqueTraits?.size ?: 0

    val rawPower = (jutsuCount * 10) +
            (natureCount * 20) +
            (traitsCount * 25)

    return rawPower.coerceIn(80, 150)
}

fun getRandomBirthMonth(): String {
    val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )
    return months.random().take(3)
}


@Composable
fun BottomSheetContent(
    selectedHero: TailedBeastModel,
    infoBoxIconColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = titleColor
) {
    val shortBio = remember(selectedHero) {
        selectedHero.getShortDescription()
    }

    val power = remember(selectedHero) {
        calculatePower(selectedHero)
    }

    val bijudama = remember(selectedHero) {
        calculateBijudamaPower(selectedHero)
    }

    val birthMonth = remember {
        getRandomBirthMonth()
    }

    Column(
        modifier = Modifier
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.app_name),
                tint = contentColor
            )
            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedHero.name,
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
                bigText = bijudama.toString(),
                smallText = stringResource(R.string.month),
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
        val abilities = if (selectedHero.id == 771) listOf(
            "Water",
            "Fire",
            "Wind",
            "Earth"
        ) else selectedHero.jutsu
        OrderListRow(
            contentColor = contentColor,
            type = selectedHero.natureType,
            abilities = abilities
        )
    }
}

@Composable
fun OrderListRow(contentColor: Color, type: List<String>?, abilities: List<String>?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        OrderedList(
            title = stringResource(R.string.family),
            items = listOf("Kazekage", "Hokage"),
            textColor = contentColor
        )

        OrderedList(
            title = stringResource(R.string.abilities),
            items = abilities ?: listOf("Fire", "Bijudama","Water","Lightning"),
            textColor = contentColor
        )

        OrderedList(
            title = stringResource(R.string.nature_types),
            items = type ?: listOf("Water", "Poison","Fire","Earth"),
            textColor = contentColor
        )

    }
}