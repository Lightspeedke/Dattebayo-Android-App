package com.jeric.narutobook.repository

import com.jeric.narutobook.domain.repository.PreferenceManager
import kotlinx.coroutines.flow.Flow

class FakePreferenceImpl: PreferenceManager {
    override suspend fun saveOnBoardingState(completed: Boolean) {
        TODO("Not yet implemented")
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

}