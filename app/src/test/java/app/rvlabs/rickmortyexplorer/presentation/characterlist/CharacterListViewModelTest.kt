package app.rvlabs.rickmortyexplorer.presentation.characterlist

import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharactersFilteredByGenderUseCase
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
class CharacterListViewModelTest {

    private val getCharactersUseCase: GetCharactersUseCase = mock()
    private val getCharactersFilteredByGenderUseCase: GetCharactersFilteredByGenderUseCase = mock()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        runTest {
            whenever(getCharactersUseCase.execute()).thenReturn(emptyList())
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun characterListViewModel_default_filtering_is_none() = runTest {
        val viewModel =
            CharacterListViewModel(getCharactersUseCase, getCharactersFilteredByGenderUseCase)

        val defaultFilter = viewModel.state.value.currentFilter
        assertEquals("none", defaultFilter)
    }

}