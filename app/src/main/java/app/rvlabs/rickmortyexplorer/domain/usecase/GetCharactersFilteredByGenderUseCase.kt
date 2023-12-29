package app.rvlabs.rickmortyexplorer.domain.usecase

import app.rvlabs.rickmortyexplorer.domain.RickMortyRepository
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel

class GetCharactersFilteredByGenderUseCase(
    private val repository: RickMortyRepository
) {

    suspend fun execute(gender: String): List<CharacterOverviewModel> {
        return repository.getCharactersFilteredByGender(gender)
    }
}