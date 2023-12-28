package app.rvlabs.rickmortyexplorer.presentation.characterdetails

import androidx.lifecycle.ViewModel
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {
}