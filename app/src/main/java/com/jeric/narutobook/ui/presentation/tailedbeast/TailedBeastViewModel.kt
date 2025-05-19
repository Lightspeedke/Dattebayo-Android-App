package com.jeric.narutobook.ui.presentation.tailedbeast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jeric.narutobook.domain.model.tail_beast.TailedBeastModel
import com.jeric.narutobook.domain.use_cases.UseCases
import com.jeric.narutobook.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TailedBeastViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedBeast: MutableStateFlow<TailedBeastModel?> = MutableStateFlow(null)
    val selectedBeast: StateFlow<TailedBeastModel?> = _selectedBeast

    private val imageId = savedStateHandle.toRoute<Routes.TailedBeast>().tailBeast

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedBeast.value = imageId.let { useCases.getSelectedTailBeastUseCase(heroId = it.toInt()) }
        }
    }

}

