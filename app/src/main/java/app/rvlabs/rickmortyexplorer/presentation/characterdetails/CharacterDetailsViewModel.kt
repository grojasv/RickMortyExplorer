package app.rvlabs.rickmortyexplorer.presentation.characterdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailsState())
    val state = _state.asStateFlow()

    fun loadCharacter(characterId: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    character = getCharacterDetailsUseCase.execute(id = characterId),
                )
            }
        }
    }

    data class CharacterDetailsState(
        val character: CharacterDetailsModel = CharacterDetailsModel(),
    )
}