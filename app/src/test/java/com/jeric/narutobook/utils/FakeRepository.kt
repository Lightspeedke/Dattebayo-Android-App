package com.jeric.narutobook.utils

import com.jeric.narutobook.data.remote.NarutoApi
import com.jeric.narutobook.data.remote.dto.akatsuki.AkatsukiResponse
import com.jeric.narutobook.data.remote.dto.buruto.BorutoResponse
import com.jeric.narutobook.data.remote.dto.character.CharacterDto
import com.jeric.narutobook.data.remote.dto.character.CharactersResponse
import com.jeric.narutobook.data.remote.dto.character.FamilyDto
import com.jeric.narutobook.data.remote.dto.character.PersonalDto
import com.jeric.narutobook.data.remote.dto.clan.ClanResponse
import com.jeric.narutobook.data.remote.dto.tailbeast.TailBeastResponse
import java.io.IOException

class FakeRepository : NarutoApi {

    private val heroes: Map<Int, List<CharacterDto>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4,
            5 to page5
        )
    }

    private var page1 = listOf(
        CharacterDto(
            id = 1,
            name = "Naruto Uzumaki",
            images = listOf("naruto1.png", "naruto2.png"),
            family = FamilyDto(
                father = "Minato Namikaze",
                mother = "Kushina Uzumaki",
                brother = null,
                daughter = "Himawari Uzumaki",
                wife = "Hinata Hyuga"
            ),
            jutsu = listOf("Rasengan", "Shadow Clone Jutsu", "Sage Mode", "Kurama Mode"),
            natureType = listOf("Wind", "Earth", "Lava", "Magnet", "Boil"),
            personal = PersonalDto(
                birthdate = "October 10",
                bloodType = "B",
                sex = "Male"
            ),
            tools = listOf("Kunai", "Scroll", "Shuriken")
        ),
        CharacterDto(
            id = 2,
            name = "Sasuke Uchiha",
            images = listOf("sasuke1.png", "sasuke2.png"),
            family = FamilyDto(
                father = "Fugaku Uchiha",
                mother = "Mikoto Uchiha",
                brother = "Itachi Uchiha",
                daughter = "Sarada Uchiha",
                wife = "Sakura Haruno"
            ),
            jutsu = listOf("Chidori", "Amaterasu", "Susanoo", "Rinnegan Techniques"),
            natureType = listOf("Fire", "Lightning", "Yin", "Deva Path"),
            personal = PersonalDto(
                birthdate = "July 23",
                bloodType = "AB",
                sex = "Male"
            ),
            tools = listOf("Sword", "Shuriken")
        ),
        CharacterDto(
            id =3,
            name = "Sakura Haruno",
            images = listOf("sakura1.png"),
            family = FamilyDto(
                father = null,
                mother = null,
                brother = null,
                daughter = "Sarada Uchiha",
                wife = null
            ),
            jutsu = listOf("Chakra Enhanced Strength", "Medical Ninjutsu"),
            natureType = listOf("Earth", "Water"),
            personal = PersonalDto(
                birthdate = "March 28",
                bloodType = "O",
                sex = "Female"
            ),
            tools = listOf("Medical Kit", "Kunai")
        ),
    )
    private var page2 = listOf(
        CharacterDto(
            id = 4,
            name = "Naruto Uzumaki",
            images = listOf("naruto1.png", "naruto2.png"),
            family = FamilyDto(
                father = "Minato Namikaze",
                mother = "Kushina Uzumaki",
                brother = null,
                daughter = "Himawari Uzumaki",
                wife = "Hinata Hyuga"
            ),
            jutsu = listOf("Rasengan", "Shadow Clone Jutsu", "Sage Mode", "Kurama Mode"),
            natureType = listOf("Wind", "Earth", "Lava", "Magnet", "Boil"),
            personal = PersonalDto(
                birthdate = "October 10",
                bloodType = "B",
                sex = "Male"
            ),
            tools = listOf("Kunai", "Scroll", "Shuriken")
        ),
        CharacterDto(
            id = 5,
            name = "Sasuke Uchiha",
            images = listOf("sasuke1.png", "sasuke2.png"),
            family = FamilyDto(
                father = "Fugaku Uchiha",
                mother = "Mikoto Uchiha",
                brother = "Itachi Uchiha",
                daughter = "Sarada Uchiha",
                wife = "Sakura Haruno"
            ),
            jutsu = listOf("Chidori", "Amaterasu", "Susanoo", "Rinnegan Techniques"),
            natureType = listOf("Fire", "Lightning", "Yin", "Deva Path"),
            personal = PersonalDto(
                birthdate = "July 23",
                bloodType = "AB",
                sex = "Male"
            ),
            tools = listOf("Sword", "Shuriken")
        ),
        CharacterDto(
            id = 6,
            name = "Sakura Haruno",
            images = listOf("sakura1.png"),
            family = FamilyDto(
                father = null,
                mother = null,
                brother = null,
                daughter = "Sarada Uchiha",
                wife = null
            ),
            jutsu = listOf("Chakra Enhanced Strength", "Medical Ninjutsu"),
            natureType = listOf("Earth", "Water"),
            personal = PersonalDto(
                birthdate = "March 28",
                bloodType = "O",
                sex = "Female"
            ),
            tools = listOf("Medical Kit", "Kunai")
        ),
    )
    private var page3 = listOf(
        CharacterDto(
            id = 7,
            name = "Naruto Uzumaki",
            images = listOf("naruto1.png", "naruto2.png"),
            family = FamilyDto(
                father = "Minato Namikaze",
                mother = "Kushina Uzumaki",
                brother = null,
                daughter = "Himawari Uzumaki",
                wife = "Hinata Hyuga"
            ),
            jutsu = listOf("Rasengan", "Shadow Clone Jutsu", "Sage Mode", "Kurama Mode"),
            natureType = listOf("Wind", "Earth", "Lava", "Magnet", "Boil"),
            personal = PersonalDto(
                birthdate = "October 10",
                bloodType = "B",
                sex = "Male"
            ),
            tools = listOf("Kunai", "Scroll", "Shuriken")
        ),
        CharacterDto(
            id = 8,
            name = "Sasuke Uchiha",
            images = listOf("sasuke1.png", "sasuke2.png"),
            family = FamilyDto(
                father = "Fugaku Uchiha",
                mother = "Mikoto Uchiha",
                brother = "Itachi Uchiha",
                daughter = "Sarada Uchiha",
                wife = "Sakura Haruno"
            ),
            jutsu = listOf("Chidori", "Amaterasu", "Susanoo", "Rinnegan Techniques"),
            natureType = listOf("Fire", "Lightning", "Yin", "Deva Path"),
            personal = PersonalDto(
                birthdate = "July 23",
                bloodType = "AB",
                sex = "Male"
            ),
            tools = listOf("Sword", "Shuriken")
        ),
        CharacterDto(
            id = 9,
            name = "Sakura Haruno",
            images = listOf("sakura1.png"),
            family = FamilyDto(
                father = null,
                mother = null,
                brother = null,
                daughter = "Sarada Uchiha",
                wife = null
            ),
            jutsu = listOf("Chakra Enhanced Strength", "Medical Ninjutsu"),
            natureType = listOf("Earth", "Water"),
            personal = PersonalDto(
                birthdate = "March 28",
                bloodType = "O",
                sex = "Female"
            ),
            tools = listOf("Medical Kit", "Kunai")
        ),
    )
    private var page4 = listOf(
        CharacterDto(
            id = 10,
            name = "Naruto Uzumaki",
            images = listOf("naruto1.png", "naruto2.png"),
            family = FamilyDto(
                father = "Minato Namikaze",
                mother = "Kushina Uzumaki",
                brother = null,
                daughter = "Himawari Uzumaki",
                wife = "Hinata Hyuga"
            ),
            jutsu = listOf("Rasengan", "Shadow Clone Jutsu", "Sage Mode", "Kurama Mode"),
            natureType = listOf("Wind", "Earth", "Lava", "Magnet", "Boil"),
            personal = PersonalDto(
                birthdate = "October 10",
                bloodType = "B",
                sex = "Male"
            ),
            tools = listOf("Kunai", "Scroll", "Shuriken")
        ),
        CharacterDto(
            id = 11,
            name = "Sasuke Uchiha",
            images = listOf("sasuke1.png", "sasuke2.png"),
            family = FamilyDto(
                father = "Fugaku Uchiha",
                mother = "Mikoto Uchiha",
                brother = "Itachi Uchiha",
                daughter = "Sarada Uchiha",
                wife = "Sakura Haruno"
            ),
            jutsu = listOf("Chidori", "Amaterasu", "Susanoo", "Rinnegan Techniques"),
            natureType = listOf("Fire", "Lightning", "Yin", "Deva Path"),
            personal = PersonalDto(
                birthdate = "July 23",
                bloodType = "AB",
                sex = "Male"
            ),
            tools = listOf("Sword", "Shuriken")
        ),
        CharacterDto(
            id = 12,
            name = "Sakura Haruno",
            images = listOf("sakura1.png"),
            family = FamilyDto(
                father = null,
                mother = null,
                brother = null,
                daughter = "Sarada Uchiha",
                wife = null
            ),
            jutsu = listOf("Chakra Enhanced Strength", "Medical Ninjutsu"),
            natureType = listOf("Earth", "Water"),
            personal = PersonalDto(
                birthdate = "March 28",
                bloodType = "O",
                sex = "Female"
            ),
            tools = listOf("Medical Kit", "Kunai")
        ),
    )
    private var page5 = listOf(
        CharacterDto(
            id = 13,
            name = "Naruto Uzumaki",
            images = listOf("naruto1.png", "naruto2.png"),
            family = FamilyDto(
                father = "Minato Namikaze",
                mother = "Kushina Uzumaki",
                brother = null,
                daughter = "Himawari Uzumaki",
                wife = "Hinata Hyuga"
            ),
            jutsu = listOf("Rasengan", "Shadow Clone Jutsu", "Sage Mode", "Kurama Mode"),
            natureType = listOf("Wind", "Earth", "Lava", "Magnet", "Boil"),
            personal = PersonalDto(
                birthdate = "October 10",
                bloodType = "B",
                sex = "Male"
            ),
            tools = listOf("Kunai", "Scroll", "Shuriken")
        ),
        CharacterDto(
            id = 14,
            name = "Sasuke Uchiha",
            images = listOf("sasuke1.png", "sasuke2.png"),
            family = FamilyDto(
                father = "Fugaku Uchiha",
                mother = "Mikoto Uchiha",
                brother = "Itachi Uchiha",
                daughter = "Sarada Uchiha",
                wife = "Sakura Haruno"
            ),
            jutsu = listOf("Chidori", "Amaterasu", "Susanoo", "Rinnegan Techniques"),
            natureType = listOf("Fire", "Lightning", "Yin", "Deva Path"),
            personal = PersonalDto(
                birthdate = "July 23",
                bloodType = "AB",
                sex = "Male"
            ),
            tools = listOf("Sword", "Shuriken")
        ),
        CharacterDto(
            id = 15,
            name = "Sakura Haruno",
            images = listOf("sakura1.png"),
            family = FamilyDto(
                father = null,
                mother = null,
                brother = null,
                daughter = "Sarada Uchiha",
                wife = null
            ),
            jutsu = listOf("Chakra Enhanced Strength", "Medical Ninjutsu"),
            natureType = listOf("Earth", "Water"),
            personal = PersonalDto(
                birthdate = "March 28",
                bloodType = "O",
                sex = "Female"
            ),
            tools = listOf("Medical Kit", "Kunai")
        ),
    )

    fun clearData() {
        page1 = emptyList()
    }

    private var exception = false

    fun addException() {
        exception = true
    }


    override suspend fun getAllCharacters(page: Int, limit: Int): CharactersResponse {
        if (exception) {
            throw IOException()
        }
        require(page in 1..5)
        return CharactersResponse(
            characters = heroes[page]!!,
            currentPage = 1,
            pageSize = page,
            total =100
        )
    }

    override suspend fun searchCharactersByName(
        name: String,
        page: Int,
        limit: Int
    ): CharactersResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(id: String): CharacterDto {
        TODO("Not yet implemented")
    }


    private fun calculate(page: Int): Map<String, Int?> {
        if (page1.isEmpty()) {
            return mapOf("prevPage" to null, "nextPage" to null)
        }
        var prevPage: Int? = page
        var nextPage: Int? = page
        if (page in 1..4) {
            nextPage = nextPage?.plus(1)
        }
        if (page in 2..5) {
            prevPage = prevPage?.minus(1)
        }
        if (page == 1) {
            prevPage = null
        }
        if (page == 5) {
            nextPage = null
        }
        return mapOf("prevPage" to prevPage, "nextPage" to nextPage)
    }



    override suspend fun getAllTailBeast(): TailBeastResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getClan(): ClanResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getAkatsuki(): AkatsukiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getBoruto(): BorutoResponse {
        TODO("Not yet implemented")
    }
}