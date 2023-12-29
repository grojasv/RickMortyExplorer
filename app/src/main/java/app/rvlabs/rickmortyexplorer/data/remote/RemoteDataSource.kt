package app.rvlabs.rickmortyexplorer.data.remote

import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel

interface RemoteDataSource {
    suspend fun getCharacters(): List<CharacterOverviewModel>
    suspend fun getCharactersFilteredByGender(gender: String): List<CharacterOverviewModel>
    suspend fun getCharacterDetails(characterId: String): CharacterDetailsModel
}