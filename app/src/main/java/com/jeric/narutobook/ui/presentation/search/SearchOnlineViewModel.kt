package com.jeric.narutobook.ui.presentation.search

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jeric.narutobook.domain.model.character.CharacterModel
import com.jeric.narutobook.domain.use_cases.UseCases
import com.jeric.narutobook.navigation.Routes
import com.jeric.narutobook.ui.presentation.components.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class SearchOnlineViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCases: UseCases
) : ViewModel() {

    private val imageId = savedStateHandle.toRoute<Routes.SearchSelected>().id

    private val _snackbarEvent = Channel<SnackbarEvent>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    private val _selectedHeroOnline: MutableStateFlow<CharacterModel?> = MutableStateFlow(null)
    val selectedHeroOnline: StateFlow<CharacterModel?> = _selectedHeroOnline

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _selectedHeroOnline.value = imageId.let { useCases.searchHeroesOnlineUseCase(id = it.toInt()) }
            } catch (e: UnknownHostException) {
                _snackbarEvent.send(
                    SnackbarEvent(message = "No Internet connection. Please check you network.")
                )
            } catch (e: Exception) {
                _snackbarEvent.send(
                    SnackbarEvent(message = "Something went wrong: ${e.message}")
                )
            }
        }
    }

}