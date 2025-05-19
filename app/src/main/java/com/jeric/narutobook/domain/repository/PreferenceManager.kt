package com.jeric.narutobook.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferenceManager {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}