package app.rvlabs.rickmortyexplorer.domain.usecase

import app.rvlabs.rickmortyexplorer.domain.RickMortyRepository
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel

class GetCharactersUseCase(
    private val repository: RickMortyRepository
) {

    suspend fun execute(): List<CharacterOverviewModel> {
        return repository.getCharacters()
    }
}