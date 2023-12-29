package app.rvlabs.rickmortyexplorer.domain.usecase

import app.rvlabs.rickmortyexplorer.domain.RickMortyRepository

class SetFavoriteCharacterUseCase(
    private val repository: RickMortyRepository
) {

    suspend fun execute(characterId: String) {
        return repository.setFavoriteCharacter(characterId)
    }
}