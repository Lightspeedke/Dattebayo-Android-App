package com.jeric.narutobook.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jeric.narutobook.domain.model.character.CharacterModel
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel
import com.jeric.narutobook.domain.use_cases.UseCases
import com.jeric.narutobook.ui.presentation.components.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    private val _snackbarEvent = Channel<SnackbarEvent>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    val characters: StateFlow<PagingData<CharacterModel>> = useCases.getAllCharacterUseCase()
        .catch { exception ->
            _snackbarEvent.send(
                SnackbarEvent(message = "Something went wrong. ${exception.message}")
            )
        }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = PagingData.empty()
        )


    val tailedBeast: StateFlow<List<TailedBeastModel>> = useCases.getAllTailBeastUseCase()
        .catch { exception ->
            _snackbarEvent.send(
                SnackbarEvent(message = "Something went wrong. ${exception.message}")
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = emptyList()
        )

}