package app.rvlabs.rickmortyexplorer.domain.usecase

import app.rvlabs.rickmortyexplorer.domain.RickMortyRepository
import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel

class GetCharacterDetailsUseCase(
    private val repository: RickMortyRepository
) {

    suspend fun execute(characterId: String): CharacterDetailsModel {
        return repository.getCharacterDetails(characterId)
    }
}