package com.jeric.narutobook.ui.presentation.buruto.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.model.buruto.KaraModel
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
class BurutoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCases: UseCases
) : ViewModel() {

    private val imageId = savedStateHandle.toRoute<Routes.KaraCharacter>().id

    private val _snackbarEvent = Channel<SnackbarEvent>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    private val _selectedKara: MutableStateFlow<KaraModel?> = MutableStateFlow(null)
    val selectedKara: StateFlow<KaraModel?> = _selectedKara

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _selectedKara.value = imageId.let { useCases.getSelectedKaraUseCase(id = it) }
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