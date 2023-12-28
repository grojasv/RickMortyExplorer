package app.rvlabs.rickmortyexplorer.presentation.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterListState())
    val state = _state.asStateFlow()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            _state.update {
                it.copy(
                    characters = getCharactersUseCase.execute(), isLoading = false
                )
            }
        }
    }

    data class CharacterListState(
        val characters: List<CharacterOverviewModel> = emptyList(), val isLoading: Boolean = false
    )

}