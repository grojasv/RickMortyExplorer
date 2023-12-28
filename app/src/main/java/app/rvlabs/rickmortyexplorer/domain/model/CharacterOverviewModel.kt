package app.rvlabs.rickmortyexplorer.domain.model

data class CharacterOverviewModel(
    val id: String = "0",
    val name: String = "",
    val gender: String = "unknown",
    val origin: String = "",
    val image: String = "",
    val favorite: Boolean = false
)
