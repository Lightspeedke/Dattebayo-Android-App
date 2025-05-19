package com.jeric.narutobook.ui.presentation.helper

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

// Define a CompositionLocal global object that will contain IOS safe area
internal val LocalSafeArea = compositionLocalOf { PaddingValues(0.dp) }