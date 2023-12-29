package app.rvlabs.rickmortyexplorer.data

import app.rvlabs.rickmortyexplorer.data.local.LocalDataSource
import app.rvlabs.rickmortyexplorer.data.remote.RemoteDataSource
import app.rvlabs.rickmortyexplorer.domain.RickMortyRepository
import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel
import javax.inject.Inject

class RickMortyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : RickMortyRepository {
    override suspend fun getCharacters(): List<CharacterOverviewModel> {
        return remoteDataSource.getCharacters().map { character ->
            val isFavorite = localDataSource.isFavoriteCharacterSaved(character.id)

            CharacterOverviewModel(
                id = character.id,
                name = character.name,
                gender = character.gender,
                origin = character.origin,
                image = character.image,
                favorite = isFavorite
            )
        }
    }

    override suspend fun getCharactersFilteredByGender(gender: String): List<CharacterOverviewModel> {
        return remoteDataSource.getCharactersFilteredByGender(gender).map { character ->
            val isFavorite = localDataSource.isFavoriteCharacterSaved(character.id)

            CharacterOverviewModel(
                id = character.id,
                name = character.name,
                gender = character.gender,
                origin = character.origin,
                image = character.image,
                favorite = isFavorite
            )
        }
    }

    override suspend fun getCharacterDetails(characterId: String): CharacterDetailsModel {
        return remoteDataSource.getCharacterDetails(characterId)
    }

    override suspend fun removeFavoriteCharacter(characterId: String) {
        return localDataSource.removeFavoriteCharacter(characterId)
    }

    override suspend fun setFavoriteCharacter(characterId: String) {
        return localDataSource.setFavoriteCharacter(characterId)
    }

    override suspend fun isFavoriteCharacterSaved(characterId: String): Boolean {
        return localDataSource.isFavoriteCharacterSaved(characterId)
    }
}