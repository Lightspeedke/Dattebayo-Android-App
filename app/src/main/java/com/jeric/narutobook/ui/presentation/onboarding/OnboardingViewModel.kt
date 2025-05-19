package com.jeric.narutobook.ui.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeric.narutobook.ui.presentation.components.state.OnboardingState
import com.jeric.narutobook.domain.repository.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class OnboardingIntent {
    data object CheckOnboardingStatus : OnboardingIntent()
    data object CompleteOnboarding : OnboardingIntent()
}

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val preferencesManager: PreferenceManager
) : ViewModel() {

    private val _state = MutableStateFlow<OnboardingState>(OnboardingState.Initial)
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    private val _isCompleted = MutableStateFlow(false)
    val isCompleted: StateFlow<Boolean> = _isCompleted.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesManager.readOnBoardingState().collectLatest { isCompleted ->
                _isCompleted.value = isCompleted
            }
        }
    }

    fun processIntent(intent: OnboardingIntent) {
        when (intent) {
            is OnboardingIntent.CheckOnboardingStatus -> checkOnboardingStatus()
            is OnboardingIntent.CompleteOnboarding -> completeOnboarding()
        }
    }

    private fun checkOnboardingStatus() {
        viewModelScope.launch {
            _state.value = OnboardingState.Loading
            try {
                preferencesManager.readOnBoardingState().collect{ isCompleted ->
                    _state.value = OnboardingState.Success(isCompleted)
                }
            } catch (e: Exception) {
                _state.value = OnboardingState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    private fun completeOnboarding() {
        viewModelScope.launch {
            try {
                preferencesManager.saveOnBoardingState(true)
                _state.value = OnboardingState.Success(true)
            } catch (e: Exception) {
                _state.value = OnboardingState.Error(e.message ?: "Failed to complete onboarding")
            }
        }
    }
} 