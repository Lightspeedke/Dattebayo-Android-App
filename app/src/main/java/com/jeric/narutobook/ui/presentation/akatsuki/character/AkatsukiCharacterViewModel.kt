package com.jeric.narutobook.ui.presentation.akatsuki.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.domain.use_cases.UseCases
import com.jeric.narutobook.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AkatsukiCharacterViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedAkatsuki: MutableStateFlow<AkatsukiModel?> = MutableStateFlow(null)
    val selectedAkatsuki: StateFlow<AkatsukiModel?> = _selectedAkatsuki

    private val imageId = savedStateHandle.toRoute<Routes.AkatsukiCharacter>().id

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedAkatsuki.value = imageId.let {
                useCases.getSelectedAkatsukiUseCase(id = it)
            }
        }
    }

}

