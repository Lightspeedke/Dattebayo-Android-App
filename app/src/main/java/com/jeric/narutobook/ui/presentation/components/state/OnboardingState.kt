package com.jeric.narutobook.ui.presentation.components.state

sealed class OnboardingState {
    data object Initial : OnboardingState()
    data object Loading : OnboardingState()
    data class Success(val isOnboardingCompleted: Boolean) : OnboardingState()
    data class Error(val message: String) : OnboardingState()
} 