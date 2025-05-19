package com.jeric.narutobook.ui.presentation.buruto.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bloodtype
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeric.narutobook.R
import com.jeric.narutobook.domain.model.buruto.KaraFamilyModel
import com.jeric.narutobook.domain.model.buruto.KaraModel
import com.jeric.narutobook.ui.presentation.character.components.AbilityItem
import com.jeric.narutobook.ui.presentation.character.components.CharacterStatsItem
import com.jeric.narutobook.ui.presentation.components.ImageState
import com.jeric.narutobook.ui.presentation.helper.LocalSafeArea
import com.jeric.narutobook.ui.presentation.tailedbeast.getRandomBirthMonth
import com.jeric.narutobook.ui.theme.Bug
import com.jeric.narutobook.ui.theme.Ice
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BurotoCharacterScreen(characterModel: KaraModel?, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { onBack() }
                    ) {
                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                    }
                },
                title = {
                    if (characterModel != null) {
                        Text(
                            text = characterModel.name.uppercase(Locale.getDefault()),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            Icons.Rounded.Favorite,
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = Color.Transparent,
        modifier = Modifier.padding(LocalSafeArea.current),
        content = {
            Box(
                modifier = Modifier
                    .padding(it)
                    .padding(20.dp)
            ) {
                if (characterModel != null) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item("image") {
                            val imageUrl =
                                if (characterModel.images?.isNotEmpty() == true)
                                    characterModel.images.last()
                                else R.drawable.onboarding_3
                            ImageState(
                                imageUrl = imageUrl,
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                                    setToSaturation(
                                        3f
                                    )
                                }),
                                modifier = Modifier
                                    .widthIn(max = 500.dp)
                                    .fillMaxWidth()
                                    .aspectRatio(1.2f)
                                    .fillMaxHeight()
                            )
                        }
                        item("name") {
                            Text(
                                text = characterModel.name.replaceFirstChar { capital -> capital.uppercaseChar() },
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.displaySmall.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        item("id") {
                            Text(
                                text = characterModel.id.toString(),
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        item("abilities") {
                            CharacterAbilityRow(
                                types = characterModel.family
                            )
                        }
                        item("infos") {
                            CharacterInfos(
                                characterModel = characterModel,
                                modifier = Modifier
                                    .padding(top = 18.dp)
                                    .fillMaxWidth(.9f)
                            )
                        }
                        item("stats") {
                            CharacterStats(
                                characterModel = characterModel,
                                modifier = Modifier
                                    .padding(top = 12.dp)
                                    .fillMaxWidth(.9f)
                            )
                        }
                    }
                }
            }
        }
    )

}

@Composable
fun CharacterAbilityRow(
    types: KaraFamilyModel?,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {

        AbilityItem(
            name = types?.father ?: "No Data Found",
            containerColor = Bug
        )


        AbilityItem(
            name = types?.mother ?: "No Data Found",
            containerColor = Ice
        )

    }
}

@Composable
fun CharacterInfos(
    characterModel: KaraModel?,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.outline.copy(alpha = .2f))
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Icon(
                    Icons.Outlined.DateRange,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = characterModel?.personal?.birthdate ?: getRandomBirthMonth(),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Birthdate",
                color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(30.dp)
                .background(color = MaterialTheme.colorScheme.outline)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Icon(
                    Icons.Outlined.Bloodtype,
                    contentDescription = null,
                    modifier = Modifier.rotate(90f)
                )
                Spacer(Modifier.width(4.dp))

                Text(
                    text = characterModel?.personal?.bloodType ?: "No data found",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Blood type",
                color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

fun getRandomBirthMonth(): String {
    val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )
    return months.random().take(3)
}


@Composable
fun CharacterStats(
    characterModel: KaraModel?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {

        characterModel?.natureType?.forEach {
            key(it) {
                CharacterStatsItem(
                    jutsu = it,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}


