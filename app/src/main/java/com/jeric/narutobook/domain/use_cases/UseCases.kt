package com.jeric.narutobook.domain.use_cases

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

data class UseCases(
    val getAllCharacterUseCase: GetAllCharacterUseCase,
    val getAllTailBeastUseCase: GetAllTailBeastUseCase,
    val getSelectedTailBeastUseCase: GetSelectedTailedBeastUseCase,
    val getSelectedCharacterUseCase: GetSelectedCharacterUseCase,
    val getAllClanUseCase: GetAllClanUseCase,
    val getSelectedClanUseCase: GetSelectedClanUseCase,
    val getAllAkatsukiUseCase: GetAllAkatsukiUseCase,
    val getSelectedAkatsukiUseCase: GetSelectedAkatsukiUseCase,
    val getAllKaraUseCase: GetAllKaraUseCase,
    val getSelectedKaraUseCase: GetSelectedKaraUseCase,
    val getSearchHeroesSource: SearchHeroesUseCase,
    val searchHeroesOnlineUseCase: SearchHeroesOnlineUseCase
)