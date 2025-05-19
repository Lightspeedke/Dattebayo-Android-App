package com.jeric.narutobook.ui.presentation.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.jeric.narutobook.domain.model.character.CharacterModel
import com.jeric.narutobook.ui.presentation.components.ImagesVerticalGrid
import com.jeric.narutobook.ui.presentation.components.ZoomedImageCard
import com.jeric.narutobook.ui.presentation.helper.LocalSafeArea
import com.jeric.narutobook.ui.presentation.home.HomeViewModel



@ExperimentalMaterial3Api
@Composable
fun CharacterScreen(
    homeViewModel: HomeViewModel,
    onImageClick: (String) -> Unit,
    onBack: () -> Unit
) {
    val character = homeViewModel.characters.collectAsLazyPagingItems()
    var showImagePreview by remember { mutableStateOf(false) }
    var activeImage by remember { mutableStateOf<CharacterModel?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Naruto",
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
                        onClick = { onBack() }, modifier = Modifier.testTag("clan_back"),
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
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValue)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImagesVerticalGrid(
                images = character,
                onImageClick = onImageClick,
                onImageDragStart = { image ->
                    activeImage = image
                    showImagePreview = true
                },
                onImageDragEnd = { showImagePreview = false },
            )
        }
        ZoomedImageCard(
            modifier = Modifier.padding(20.dp),
            isVisible = showImagePreview,
            image = activeImage
        )
    }


}