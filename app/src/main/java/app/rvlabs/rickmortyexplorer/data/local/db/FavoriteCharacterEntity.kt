package app.rvlabs.rickmortyexplorer.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCharacterEntity(
    @PrimaryKey
    val id: String = "0"
)