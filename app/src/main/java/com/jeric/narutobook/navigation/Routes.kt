package com.jeric.narutobook.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class Routes {
    @Serializable
    data object Splash : Routes()
    @Serializable
    data object Onboarding : Routes()
    @Serializable
    data object HomeScreen : Routes()
    @Serializable
    data object SearchScreen : Routes()
    @Serializable
    data object Clan : Routes()
    @Serializable
    data object Character : Routes()
    @Serializable
    data object Kara : Routes()
    @Serializable
    data object TeamAkatsuki : Routes()

    @Serializable
    data class TailedBeast(val tailBeast: String) : Routes()

    @Serializable
    data class CharacterFullScreen(val image: String) : Routes()

    @Serializable
    data class AkatsukiCharacter(val id: Int) : Routes()

    @Serializable
    data class KaraCharacter(val id: Int) : Routes()

    @Serializable
    data class SearchSelected(val id: String) : Routes()

}