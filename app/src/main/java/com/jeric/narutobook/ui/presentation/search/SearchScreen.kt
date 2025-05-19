package com.jeric.narutobook.ui.presentation.search

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel,
    onClick: (String) -> Unit
) {
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                },
            )
        },
        content = { padding ->
            ListContent(padding = padding, heroes = heroes, onClick = onClick)
        },
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
    )
}
