package app.rvlabs.rickmortyexplorer.domain

import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel

interface RickMortyRepository {

    suspend fun getCharacters(): List<CharacterOverviewModel>

    suspend fun getCharacterDetails(id: String): CharacterDetailsModel
}