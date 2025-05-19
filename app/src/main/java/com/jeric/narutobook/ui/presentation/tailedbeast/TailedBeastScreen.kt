package com.jeric.narutobook.ui.presentation.tailedbeast

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@ExperimentalMaterial3Api
@Composable
fun TailedBeastScreen(navigate: () -> Unit, detailsViewModel: TailedBeastViewModel) {
    val selectedHero by detailsViewModel.selectedBeast.collectAsStateWithLifecycle()
    TailedBeastContent(
        onBack = { navigate() },
        selectedTailedBeast = selectedHero,
    )
}