package app.rvlabs.rickmortyexplorer.domain.usecase

import app.rvlabs.rickmortyexplorer.domain.RickMortyRepository

class IsFavoriteCharacterSavedUseCase(
    private val repository: RickMortyRepository
) {

    suspend fun execute(characterId: String): Boolean {
        return repository.isFavoriteCharacterSaved(characterId)
    }
}