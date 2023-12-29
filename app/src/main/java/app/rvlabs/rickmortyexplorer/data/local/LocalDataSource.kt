package app.rvlabs.rickmortyexplorer.data.local

interface LocalDataSource {
    suspend fun removeFavoriteCharacter(characterId: String)
    suspend fun setFavoriteCharacter(characterId: String)
    suspend fun isFavoriteCharacterSaved(characterId: String): Boolean
}