package com.jeric.narutobook.domain.use_cases.get_all_tailbeast

import com.jeric.narutobook.data.repository.RepositoryImpl
import com.jeric.narutobook.domain.use_cases.get_all_akatsuki.GetSelectedAkatsukiUseCase
import com.jeric.narutobook.domain.use_cases.get_all_heroes.GetSelectedCharacterUseCase
import com.jeric.narutobook.domain.use_cases.get_all_kara.GetSelectedKaraUseCase
import com.jeric.narutobook.repository.FakePreferenceImpl
import com.jeric.narutobook.repository.FakeRepositoryImpl
import com.jeric.narutobook.utils.akatsukiList
import com.jeric.narutobook.utils.characterList
import com.jeric.narutobook.utils.getTailedBeast
import com.jeric.narutobook.utils.karaList
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllTailBeastUseCaseTest{
    private lateinit var fakeSuccessRepo: RepositoryImpl
    private lateinit var remoteDataSource: FakeRepositoryImpl
    private lateinit var dataSource: FakePreferenceImpl
    private lateinit var getSelectedTailedBeastUseCase: GetSelectedTailedBeastUseCase
    private lateinit var getSelectedKaraUseCase: GetSelectedKaraUseCase
    private lateinit var getSelectedAkatsukiUseCase: GetSelectedAkatsukiUseCase
    private lateinit var getSelectedCharacterUseCase: GetSelectedCharacterUseCase

    @Before
    fun setUp(){
        remoteDataSource = FakeRepositoryImpl()
        dataSource = FakePreferenceImpl()
        fakeSuccessRepo = RepositoryImpl(remote = remoteDataSource, dataStore = dataSource)
        getSelectedTailedBeastUseCase = GetSelectedTailedBeastUseCase(repository = fakeSuccessRepo)
        getSelectedKaraUseCase = GetSelectedKaraUseCase(repository = fakeSuccessRepo)
        getSelectedAkatsukiUseCase = GetSelectedAkatsukiUseCase(repository = fakeSuccessRepo)
        getSelectedCharacterUseCase = GetSelectedCharacterUseCase(repository = fakeSuccessRepo)
    }

    @Test
    fun `should return the correct TailedBeast when id is provided`() = runTest {
        // Arrange
        val expectedId = 1
        val expectedTailedBeast = getTailedBeast().find { it.id == expectedId }

        // Act
        val result = getSelectedTailedBeastUseCase(expectedId)

        // Assert
        assertEquals(expectedTailedBeast, result)
    }

    @Test
    fun `should return the correct Kara when id is provided`() = runTest {
        // Arrange
        val expectedId = 1
        val expectedTailedBeast = karaList().find { it.id == expectedId }

        // Act
        val result = getSelectedKaraUseCase(expectedId)

        // Assert
        assertEquals(expectedTailedBeast, result)
    }

    @Test
    fun `should return the correct Akatsuki when id is provided`() = runTest {
        // Arrange
        val expectedId = 1
        val expectedTailedBeast = akatsukiList().find { it.id == expectedId }

        // Act
        val result = getSelectedAkatsukiUseCase(expectedId)

        // Assert
        assertEquals(expectedTailedBeast, result)
    }

    @Test
    fun `should return the correct Character when id is provided`() = runTest {
        // Arrange
        val expectedId = 1
        val expectedTailedBeast = characterList().find { it.id.toInt() == expectedId }

        // Act
        val result = getSelectedCharacterUseCase(expectedId)

        // Assert
        assertEquals(expectedTailedBeast, result)
    }

}