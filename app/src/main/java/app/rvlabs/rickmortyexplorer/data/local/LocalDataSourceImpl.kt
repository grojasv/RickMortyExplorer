package app.rvlabs.rickmortyexplorer.data.local

import app.rvlabs.rickmortyexplorer.data.local.db.FavoriteCharacterDao
import app.rvlabs.rickmortyexplorer.data.local.db.FavoriteCharacterEntity

class LocalDataSourceImpl(
    private val favoriteCharacterDao: FavoriteCharacterDao
) : LocalDataSource {
    override suspend fun removeFavoriteCharacter(characterId: String) {
        val characterToDelete = FavoriteCharacterEntity(characterId)
        favoriteCharacterDao.deleteFavoriteCharacter(characterToDelete)
    }

    override suspend fun setFavoriteCharacter(characterId: String) {
        val characterToInsert = FavoriteCharacterEntity(characterId)
        favoriteCharacterDao.insertFavoriteCharacter(characterToInsert)
    }

    override suspend fun isFavoriteCharacterSaved(characterId: String): Boolean {
        return favoriteCharacterDao.isFavoriteCharacterSaved(characterId)
    }
}