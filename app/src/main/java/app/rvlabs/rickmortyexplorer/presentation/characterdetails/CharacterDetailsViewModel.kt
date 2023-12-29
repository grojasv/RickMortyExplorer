package app.rvlabs.rickmortyexplorer.presentation.characterdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharacterDetailsUseCase
import app.rvlabs.rickmortyexplorer.domain.usecase.IsFavoriteCharacterSavedUseCase
import app.rvlabs.rickmortyexplorer.domain.usecase.RemoveFavoriteCharacterUseCase
import app.rvlabs.rickmortyexplorer.domain.usecase.SetFavoriteCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val setFavoriteCharacterUseCase: SetFavoriteCharacterUseCase,
    private val removeFavoriteCharacterUseCase: RemoveFavoriteCharacterUseCase,
    private val isFavoriteCharacterSavedUseCase: IsFavoriteCharacterSavedUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailsState())
    val state = _state.asStateFlow()

    fun loadCharacter(characterId: String) {
        viewModelScope.launch {
            val character = getCharacterDetailsUseCase.execute(characterId = characterId)
            val isFavoriteCharacterSaved =
                isFavoriteCharacterSavedUseCase.execute(characterId = characterId)

            _state.update {
                it.copy(
                    character = character,
                    isFavorite = isFavoriteCharacterSaved
                )
            }
        }
    }

    fun favoriteToggleClicked() {
        val newFavoriteValue = _state.value.isFavorite.not()

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isFavorite = newFavoriteValue
                )
            }
            if (newFavoriteValue) {
                setFavoriteCharacterUseCase.execute(state.value.character.id)
            } else {
                removeFavoriteCharacterUseCase.execute(state.value.character.id)
            }
        }
    }

    data class CharacterDetailsState(
        val character: CharacterDetailsModel = CharacterDetailsModel(),
        val isFavorite: Boolean = false
    )
}