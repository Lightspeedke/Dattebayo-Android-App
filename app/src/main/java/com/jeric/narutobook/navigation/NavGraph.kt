package com.jeric.narutobook.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jeric.narutobook.ui.presentation.akatsuki.AkatsukiScreen
import com.jeric.narutobook.ui.presentation.akatsuki.AkatsukiViewModel
import com.jeric.narutobook.ui.presentation.akatsuki.character.AkatsukiCharacterScreen
import com.jeric.narutobook.ui.presentation.akatsuki.character.AkatsukiCharacterViewModel
import com.jeric.narutobook.ui.presentation.buruto.KaraScreen
import com.jeric.narutobook.ui.presentation.buruto.KaraViewModel
import com.jeric.narutobook.ui.presentation.buruto.character.BurotoCharacterScreen
import com.jeric.narutobook.ui.presentation.buruto.character.BurutoViewModel
import com.jeric.narutobook.ui.presentation.character.CharacterScreen
import com.jeric.narutobook.ui.presentation.clan.ClanScreen
import com.jeric.narutobook.ui.presentation.clan.ClanViewModel
import com.jeric.narutobook.ui.presentation.fullscreen.FullImageScreen
import com.jeric.narutobook.ui.presentation.fullscreen.FullImageViewModel
import com.jeric.narutobook.ui.presentation.home.HomeScreen
import com.jeric.narutobook.ui.presentation.home.HomeViewModel
import com.jeric.narutobook.ui.presentation.onboarding.OnboardingScreen
import com.jeric.narutobook.ui.presentation.onboarding.OnboardingViewModel
import com.jeric.narutobook.ui.presentation.search.SearchOnlineViewModel
import com.jeric.narutobook.ui.presentation.search.SearchScreen
import com.jeric.narutobook.ui.presentation.search.SearchViewModel
import com.jeric.narutobook.ui.presentation.splash.SplashScreen
import com.jeric.narutobook.ui.presentation.tailedbeast.TailedBeastScreen
import com.jeric.narutobook.ui.presentation.tailedbeast.TailedBeastViewModel

@ExperimentalMaterial3Api
@Composable
fun NavGraphSetup(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        composable<Routes.Splash> {
            val viewModel: OnboardingViewModel = hiltViewModel()
            val isCompleted by viewModel.isCompleted.collectAsStateWithLifecycle()
            SplashScreen(
                onSplashFinished = {
                    val route = if (isCompleted) Routes.HomeScreen else Routes.Onboarding
                    navController.navigate(route) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.Onboarding> {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(Routes.HomeScreen) {
                        popUpTo(Routes.Onboarding) { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.HomeScreen> {
            val homeViewModel: HomeViewModel = hiltViewModel()

            val items = listOf("Home" to Icons.Outlined.Home, "Favorite" to Icons.Outlined.Favorite)
            val selectedItem by remember { mutableStateOf(items[0]) }

            HomeScreen(
                homeViewModel = homeViewModel,
                onClanClick = { navController.navigate(Routes.Clan) },
                onCharacterClick = { navController.navigate(Routes.Character) },
                onVillagesClick = { navController.navigate(Routes.Kara) },
                onTeamClick = { navController.navigate(Routes.TeamAkatsuki) },
                onTailBeastClick = { navController.navigate(Routes.TailedBeast(it)) },
                selectedItem = selectedItem,
                items = items,
                onClickSearch = { navController.navigate(Routes.SearchScreen) }
            )
        }

        composable<Routes.Clan> {
            val clanViewModel: ClanViewModel = hiltViewModel()
            ClanScreen(clanModel = clanViewModel, onBack =  { navController.navigateUp() })
        }
        composable<Routes.Character> {
            val homeViewModel: HomeViewModel = hiltViewModel()
            CharacterScreen(
                homeViewModel = homeViewModel,
                onImageClick = { imageId ->
                    navController.navigate(Routes.CharacterFullScreen(imageId))
                },
                onBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Kara> {
            val karaViewModel: KaraViewModel = hiltViewModel()
            KaraScreen(
                karaViewModel = karaViewModel,
                onBack = { navController.navigateUp() },
                onClickITem = {
                    navController.navigate(Routes.KaraCharacter(it))
                }
            )
        }

        composable<Routes.KaraCharacter> {
            val karaViewModel: BurutoViewModel = hiltViewModel()
            val kara by karaViewModel.selectedKara.collectAsStateWithLifecycle()
            BurotoCharacterScreen(
                characterModel = kara,
                onBack = { navController.navigateUp() },
            )
        }
        composable<Routes.TeamAkatsuki> {
            val akatsukiModel: AkatsukiViewModel = hiltViewModel()
            AkatsukiScreen(
                akatsukiModel = akatsukiModel,
                onBack = { navController.navigateUp() },
                onClickITem = { karaId ->
                    navController.navigate(Routes.AkatsukiCharacter(karaId))
                }
            )
        }
        composable<Routes.AkatsukiCharacter> {
            val akatsukiModel: AkatsukiCharacterViewModel = hiltViewModel()
            val akatsuki by akatsukiModel.selectedAkatsuki.collectAsStateWithLifecycle()
            AkatsukiCharacterScreen(
                akatsukiModel = akatsuki,
                onBack = { navController.navigateUp() },
            )
        }
        composable<Routes.TailedBeast> {
            val detailsViewModel: TailedBeastViewModel = hiltViewModel()
            TailedBeastScreen(
                navigate = { navController.navigateUp() },
                detailsViewModel = detailsViewModel
            )
        }
        composable<Routes.CharacterFullScreen> {
            val fullScreen: FullImageViewModel = hiltViewModel()
            val selectedHero by fullScreen.selectedHero.collectAsStateWithLifecycle()
            FullImageScreen(characterModel = selectedHero, onBack = { navController.navigateUp() })
        }
        composable<Routes.SearchScreen> {
            val searchScreen: SearchViewModel = hiltViewModel()
            SearchScreen(
                navController = navController,
                searchViewModel = searchScreen,
                onClick = { navController.navigate(Routes.SearchSelected(it)) }
            )
        }
        composable<Routes.SearchSelected> {
            val fullScreen: SearchOnlineViewModel = hiltViewModel()
            val selectedHero by fullScreen.selectedHeroOnline.collectAsStateWithLifecycle()
            FullImageScreen(characterModel = selectedHero, onBack = { navController.navigateUp() })
        }

    }
} 