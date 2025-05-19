@file:OptIn(ExperimentalMaterial3Api::class)

package com.jeric.narutobook.ui.presentation.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jeric.narutobook.domain.model.character.CharacterModel
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel
import com.jeric.narutobook.ui.presentation.components.CategoryButton
import com.jeric.narutobook.ui.presentation.components.MainModalDrawerSheet
import com.jeric.narutobook.ui.presentation.components.TailBeastItem
import com.jeric.narutobook.ui.presentation.components.state.CategoryState
import com.jeric.narutobook.ui.presentation.helper.LocalSafeArea
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onClanClick: () -> Unit,
    onCharacterClick: () -> Unit,
    onVillagesClick: () -> Unit,
    onTeamClick: () -> Unit,
    onTailBeastClick: (String) -> Unit,
    homeViewModel: HomeViewModel,
    selectedItem: Pair<String, ImageVector>,
    items: List<Pair<String, ImageVector>>,
    onClickSearch: () -> Unit
) {
    val beast by homeViewModel.tailedBeast.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MainModalDrawerSheet(
                items = items,
                selectedItem = selectedItem,
                onItemsClick = { item ->
                    scope.launch { drawerState.close() }
                }
            )
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch { drawerState.open() }
                                },
                            ) {
                                Icon(Icons.Rounded.Menu, contentDescription = null)
                            }
                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    )
                },
                modifier = Modifier.padding(LocalSafeArea.current)
            ) { paddingValues ->
                MainContent(
                    beast = beast,
                    onClanClick = onClanClick,
                    onCharacterClick = onCharacterClick,
                    onVillagesClick = onVillagesClick,
                    onTeamClick = onTeamClick,
                    onTailBeastClick = onTailBeastClick,
                    onClickSearch = onClickSearch,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    )
}




@Composable
fun MainContent(
    beast: List<TailedBeastModel>,
    onClanClick: () -> Unit,
    onCharacterClick: () -> Unit,
    onVillagesClick: () -> Unit,
    onTeamClick: () -> Unit,
    onTailBeastClick: (String) -> Unit,
    onClickSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .5f)
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Text(
            text ="Which Naruto character are you looking for?",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.ExtraBold,
            ),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
        )
        TextField(
            value = "",
            onValueChange = {

            },
            placeholder = { Text(text = "Search Character") },
            leadingIcon = {
                IconButton(onClick = { onClickSearch() }) {
                    Icon(Icons.Rounded.Search, contentDescription = "Search Character")
                }
            },
            readOnly = true, // Prevents typing, makes it feel like a button
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLeadingIconColor = MaterialTheme.colorScheme.surface,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.surface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.surface,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.surface,
            ),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .clickable { onClickSearch() } // Navigate on click
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            CategoryButton(
                onClick = { onClanClick() },
                categoryState = CategoryState.pokedex,
                modifier = Modifier.weight(1f)
                    .testTag("button_pokedex")
                ,
            )

            CategoryButton(
                onClick = { onCharacterClick() },
                categoryState = CategoryState.moves,
                modifier = Modifier.weight(1f)
                    .testTag("button_moves")
                ,
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            CategoryButton(
                onClick = { onVillagesClick() },
                categoryState = CategoryState.evolutions,
                modifier = Modifier.weight(1f)
                    .testTag("button_evolutions")
                ,
            )

            CategoryButton(
                onClick = { onTeamClick() },
                categoryState = CategoryState.locations,
                modifier = Modifier.weight(1f)
                    .testTag("button_locations")
                ,
            )
        }

        Text(
            text = "Tailed Beast",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 6.dp)
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            color = MaterialTheme.colorScheme.outline.copy(alpha = .4f)
        )

        TailedBeastRow(
            tailedBeastList = beast,
            onClickITem = { onTailBeastClick(it) }
        )
    }
}


@Composable
fun TailedBeastRow(
    modifier: Modifier = Modifier,
    tailedBeastList: List<TailedBeastModel>,
    onClickITem: (id: String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(10.dp),
        modifier = modifier
            .testTag("tailedBeastTag")
    ) {
        items(tailedBeastList, key = { it.id }) { item ->
            TailBeastItem(
                onClick = { onClickITem(item.id.toString()) },
                tailedBeastModel = item,
                modifier = Modifier.testTag(item.id.toString())
            )
        }
    }
}

fun CharacterModel.getShortBio(): String {
    val birth = personal?.birthdate ?: "an unknown date"
    val sex = personal?.sex ?: "Unknown"
    val blood = personal?.bloodType ?: "Unknown"
    val father = family?.father ?: "an unknown father"
    val mother = family?.mother ?: "an unknown mother"
    val jutsuCount = jutsu?.size ?: 0
    val natureCount = natureType?.size ?: 0

    return "$name was born on $birth. A $sex ninja with blood type $blood, " +
            "$name is the child of $father and $mother. Known for mastering $jutsuCount jutsu " +
            "and wielding $natureCount chakra nature types, $name is a notable figure in the Naruto universe."
}

