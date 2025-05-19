package com.jeric.narutobook.ui.presentation.buruto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeric.narutobook.domain.model.akatsuki.AkatsukiModel
import com.jeric.narutobook.domain.model.buruto.KaraModel
import com.jeric.narutobook.domain.model.clan.ClanModel
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
class KaraViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {

    private val _snackbarEvent = Channel<SnackbarEvent>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()


    val karaModel: StateFlow<List<KaraModel>> = useCases.getAllKaraUseCase()
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