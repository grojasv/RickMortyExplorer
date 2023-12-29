package app.rvlabs.rickmortyexplorer.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteCharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteCharacter(favoriteCharacterEntity: FavoriteCharacterEntity)

    @Delete
    suspend fun deleteFavoriteCharacter(favoriteCharacterEntity: FavoriteCharacterEntity)

    @Query("SELECT EXISTS(SELECT * FROM FavoriteCharacterEntity WHERE id  = :characterId)")
    suspend fun isFavoriteCharacterSaved(characterId: String): Boolean
}