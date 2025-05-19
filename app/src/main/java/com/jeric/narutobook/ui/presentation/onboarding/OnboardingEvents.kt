package com.jeric.narutobook.ui.presentation.onboarding

sealed class OnboardingEvents {
    data object CheckOnboardingStatus : OnboardingEvents()
    data object CompleteOnboarding : OnboardingEvents()
    data class OnPageChanged(val page: Int) : OnboardingEvents()
} 