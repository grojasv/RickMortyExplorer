package app.rvlabs.rickmortyexplorer.domain

import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel

interface RickMortyRepository {

    suspend fun getCharacters(): List<CharacterOverviewModel>
    suspend fun getCharactersFilteredByGender(gender: String): List<CharacterOverviewModel>
    suspend fun getCharacterDetails(characterId: String): CharacterDetailsModel
    suspend fun removeFavoriteCharacter(characterId: String)
    suspend fun setFavoriteCharacter(characterId: String)
    suspend fun isFavoriteCharacterSaved(characterId: String): Boolean
}