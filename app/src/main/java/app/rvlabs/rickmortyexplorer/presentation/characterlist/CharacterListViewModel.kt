package app.rvlabs.rickmortyexplorer.presentation.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.rvlabs.rickmortyexplorer.core.Constants.GENDER_FEMALE
import app.rvlabs.rickmortyexplorer.core.Constants.GENDER_GENDERLESS
import app.rvlabs.rickmortyexplorer.core.Constants.GENDER_MALE
import app.rvlabs.rickmortyexplorer.core.Constants.GENDER_NONE
import app.rvlabs.rickmortyexplorer.core.Constants.GENDER_UNKNOWN
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharactersFilteredByGenderUseCase
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharactersFilteredByGenderUseCase: GetCharactersFilteredByGenderUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterListState())
    val state = _state.asStateFlow()

    private var currentFilterIndex = 0
    private val filterOptions =
        arrayOf(GENDER_NONE, GENDER_FEMALE, GENDER_MALE, GENDER_GENDERLESS, GENDER_UNKNOWN)

    init {
        refreshCharacters()
    }

    fun refreshCharacters() {
        val currentGenderFilter = filterOptions[currentFilterIndex]
        if (currentGenderFilter.equals(GENDER_NONE, ignoreCase = true)) {
            loadCharactersWithoutFilter()
        } else {
            loadCharactersFilteredByGender(currentGenderFilter)
        }
    }

    private fun loadCharactersWithoutFilter() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    currentFilter = GENDER_NONE,
                )
            }
            _state.update {
                it.copy(
                    isLoading = false, characters = getCharactersUseCase.execute()
                )
            }
        }
    }

    private fun loadCharactersFilteredByGender(gender: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true, currentFilter = gender
                )
            }
            _state.update {
                it.copy(
                    isLoading = false,
                    characters = getCharactersFilteredByGenderUseCase.execute(gender)
                )
            }
        }
    }

    fun applyNextFilter() {
        currentFilterIndex++
        if (currentFilterIndex >= filterOptions.size) {
            currentFilterIndex = 0
        }

        refreshCharacters()
    }

    data class CharacterListState(
        val characters: List<CharacterOverviewModel> = emptyList(),
        val isLoading: Boolean = false,
        var currentFilter: String = GENDER_NONE
    )

}