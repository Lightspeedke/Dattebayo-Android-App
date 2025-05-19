@file:OptIn(ExperimentalMaterial3Api::class)

package com.jeric.narutobook

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.SavedStateHandle
import com.jeric.narutobook.data.repository.RepositoryImpl
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
import com.jeric.narutobook.repository.FakePreferenceImpl
import com.jeric.narutobook.repository.FakeRepositoryImpl
import com.jeric.narutobook.ui.presentation.akatsuki.AkatsukiScreen
import com.jeric.narutobook.ui.presentation.akatsuki.AkatsukiViewModel
import com.jeric.narutobook.ui.presentation.buruto.KaraScreen
import com.jeric.narutobook.ui.presentation.buruto.KaraViewModel
import com.jeric.narutobook.ui.presentation.character.CharacterScreen
import com.jeric.narutobook.ui.presentation.clan.ClanScreen
import com.jeric.narutobook.ui.presentation.clan.ClanViewModel
import com.jeric.narutobook.ui.presentation.home.HomeScreen
import com.jeric.narutobook.ui.presentation.home.HomeViewModel
import com.jeric.narutobook.utils.akatsukiList
import com.jeric.narutobook.utils.characterList
import com.jeric.narutobook.utils.clanList
import com.jeric.narutobook.utils.getTailedBeast
import com.jeric.narutobook.utils.karaList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UseCasesTest {
    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var getAllAkatsukiUseCase: GetAllAkatsukiUseCase
    private lateinit var getSelectedAkatsukiUseCase: GetSelectedAkatsukiUseCase
    private lateinit var getAllClanUseCase: GetAllClanUseCase
    private lateinit var getSelectedClanUseCase: GetSelectedClanUseCase
    private lateinit var getAllCharacterUseCase: GetAllCharacterUseCase
    private lateinit var getSelectedCharacterUseCase: GetSelectedCharacterUseCase
    private lateinit var getAllKaraUseCase: GetAllKaraUseCase
    private lateinit var getSelectedKaraUseCase: GetSelectedKaraUseCase
    private lateinit var getAllTailBeastUseCase: GetAllTailBeastUseCase
    private lateinit var getSelectedTailedBeastUseCase: GetSelectedTailedBeastUseCase
    private lateinit var getSearchHeroesUseCase: SearchHeroesUseCase
    private lateinit var searchHeroesOnlineUseCase: SearchHeroesOnlineUseCase

    private lateinit var getAllUseCases: UseCases

    private lateinit var fakeSuccessRepo: RepositoryImpl
    private lateinit var remoteDataSource: FakeRepositoryImpl
    private lateinit var dataSource: FakePreferenceImpl
    val savedStateHandle = SavedStateHandle(mapOf("characterId" to "naruto-uzumaki"))

    @Before
    fun setUp() {
        remoteDataSource = FakeRepositoryImpl()
        dataSource = FakePreferenceImpl()
        fakeSuccessRepo = RepositoryImpl(remote = remoteDataSource, dataStore = dataSource)
        getAllKaraUseCase = GetAllKaraUseCase(repository = fakeSuccessRepo)
        getAllAkatsukiUseCase = GetAllAkatsukiUseCase(repository = fakeSuccessRepo)
        getSelectedAkatsukiUseCase = GetSelectedAkatsukiUseCase(repository = fakeSuccessRepo)
        getAllClanUseCase = GetAllClanUseCase(repository = fakeSuccessRepo)
        getSelectedClanUseCase = GetSelectedClanUseCase(repository = fakeSuccessRepo)
        getAllCharacterUseCase = GetAllCharacterUseCase(repository = fakeSuccessRepo)
        getSelectedCharacterUseCase = GetSelectedCharacterUseCase(repository = fakeSuccessRepo)
        getSelectedKaraUseCase = GetSelectedKaraUseCase(repository = fakeSuccessRepo)
        getAllTailBeastUseCase = GetAllTailBeastUseCase(repository = fakeSuccessRepo)
        getSelectedTailedBeastUseCase = GetSelectedTailedBeastUseCase(repository = fakeSuccessRepo)
        getSearchHeroesUseCase = SearchHeroesUseCase(repository = fakeSuccessRepo)
        searchHeroesOnlineUseCase = SearchHeroesOnlineUseCase(repository = fakeSuccessRepo)

        getAllUseCases = UseCases(
            getAllKaraUseCase = getAllKaraUseCase,
            getAllClanUseCase = getAllClanUseCase,
            getAllAkatsukiUseCase = getAllAkatsukiUseCase,
            getSelectedKaraUseCase = getSelectedKaraUseCase,
            getSelectedAkatsukiUseCase = getSelectedAkatsukiUseCase,
            getSelectedClanUseCase = getSelectedClanUseCase,
            getSelectedCharacterUseCase = getSelectedCharacterUseCase,
            getSelectedTailBeastUseCase = getSelectedTailedBeastUseCase,
            getAllTailBeastUseCase = getAllTailBeastUseCase,
            getAllCharacterUseCase = getAllCharacterUseCase,
            getSearchHeroesSource = getSearchHeroesUseCase,
            searchHeroesOnlineUseCase = searchHeroesOnlineUseCase
        )
    }

    @Test
    fun testHomeScreen() {
        val homeViewModel = HomeViewModel(useCases = getAllUseCases)
        composeRule.setContent {
            HomeScreen(
                onClanClick = {  },
                onCharacterClick = {},
                onVillagesClick = {},
                onTeamClick = {},
                onTailBeastClick = {},
                homeViewModel = homeViewModel,
                selectedItem = "Home" to Icons.Default.Home,
                items = listOf("Home" to Icons.Default.Home),
                onClickSearch = {}
            )
        }
        with(composeRule) {
            onNodeWithTag("button_pokedex").performClick()
            onNodeWithTag("button_moves").performClick()
            onNodeWithTag("button_evolutions").performClick()
            onNodeWithTag("button_locations").performClick()
            onNodeWithTag("tailedBeastTag").assertIsDisplayed()
            onNodeWithTag("tailedBeastTag")
                .onChildAt(0).assert(hasTestTag(getTailedBeast().first().id.toString()))
        }
    }

    @Test
    fun testKaraScreen() {
        val karaViewModel =  KaraViewModel(useCases = getAllUseCases)
        composeRule.setContent {
            KaraScreen(
                karaViewModel = karaViewModel,
                onBack = {  },
                onClickITem = {

                }
            )
        }
        with(composeRule) {
            onNodeWithTag("kara_back").performClick()
            onNodeWithTag("kara_col").assertIsDisplayed()
            onNodeWithTag("kara_col")
                .onChildAt(0).assert(hasTestTag(karaList().first().id.toString()))
        }
    }

    @Test
    fun testAkatsukiScreen() {
        val karaViewModel =  AkatsukiViewModel(useCases = getAllUseCases)
        composeRule.setContent {
            AkatsukiScreen(
                akatsukiModel = karaViewModel,
                onBack = {  },
                onClickITem = {}
            )
        }
        with(composeRule) {
            onNodeWithTag("akatsuki_back").performClick()
            onNodeWithTag("akatsuki_col").assertIsDisplayed()
            onNodeWithTag("akatsuki_col")
                .onChildAt(0).assert(hasTestTag(akatsukiList().first().id.toString()))
        }
    }

    @Test
    fun testCharacterScreen() {
        val homeViewModel =  HomeViewModel(useCases = getAllUseCases)
        composeRule.setContent {
            CharacterScreen (
                homeViewModel = homeViewModel,
                onImageClick = {},
                onBack = {}
            )
        }
        with(composeRule) {
            onNodeWithTag("home_col").assertIsDisplayed()
            onNodeWithTag("home_col")
                .onChildAt(0).assert(hasTestTag(characterList().first().id))
        }
    }

    @Test
    fun testClanScreen() {
        val clanViewModel =  ClanViewModel(useCases = getAllUseCases)
        composeRule.setContent {
            ClanScreen (
                clanModel = clanViewModel,
                onBack = {}
            )
        }
        with(composeRule) {
            onNodeWithTag("clan_back").performClick()
            onNodeWithTag("clan_col").assertIsDisplayed()
            onNodeWithTag("clan_col")
                .onChildAt(0).assert(hasTestTag(clanList().first().id.toString()))
        }
    }

}