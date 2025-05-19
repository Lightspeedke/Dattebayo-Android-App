package com.jeric.narutobook.di

import android.content.Context
import com.jeric.narutobook.data.repository.PreferencesManagerImpl
import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.repository.PreferenceManager
import com.jeric.narutobook.domain.use_cases.UseCases
import com.jeric.narutobook.domain.use_cases.get_all_akatsuki.GetAllAkatsukiUseCase
import com.jeric.narutobook.domain.use_cases.get_all_akatsuki.GetSelectedAkatsukiUseCase
import com.jeric.narutobook.domain.use_cases.get_all_clan.GetAllClanUseCase
import com.jeric.narutobook.domain.use_cases.get_all_clan.GetSelectedClanUseCase
import com.jeric.narutobook.domain.use_cases.get_all_heroes.GetAllCharacterUseCase
import com.jeric.narutobook.domain.use_cases.get_all_heroes.GetSelectedCharacterUseCase
import com.jeric.narutobook.domain.use_cases.get_all_kara.GetAllKaraUseCase
import com.jeric.narutobook.domain.use_cases.get_all_kara.GetSelectedKaraUseCase
import com.jeric.narutobook.domain.use_cases.get_all_tailbeast.GetAllTailBeastUseCase
import com.jeric.narutobook.domain.use_cases.get_all_tailbeast.GetSelectedTailedBeastUseCase
import com.jeric.narutobook.domain.use_cases.search_heroes.SearchHeroesOnlineUseCase
import com.jeric.narutobook.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesMngr {

    @Provides
    @Singleton
    fun preferencesManager(
        @ApplicationContext context: Context
    ): PreferenceManager {
        return PreferencesManagerImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: RepositoryImpl): UseCases {
        return UseCases(
            getAllCharacterUseCase = GetAllCharacterUseCase(repository),
            getAllTailBeastUseCase = GetAllTailBeastUseCase(repository),
            getSelectedTailBeastUseCase = GetSelectedTailedBeastUseCase(repository),
            getSelectedCharacterUseCase = GetSelectedCharacterUseCase(repository),
            getSelectedClanUseCase = GetSelectedClanUseCase(repository),
            getAllClanUseCase = GetAllClanUseCase(repository),
            getAllAkatsukiUseCase = GetAllAkatsukiUseCase(repository),
            getSelectedAkatsukiUseCase = GetSelectedAkatsukiUseCase(repository),
            getAllKaraUseCase = GetAllKaraUseCase(repository),
            getSelectedKaraUseCase = GetSelectedKaraUseCase(repository),
            getSearchHeroesSource = SearchHeroesUseCase(repository),
            searchHeroesOnlineUseCase = SearchHeroesOnlineUseCase(repository)
        )
    }
}