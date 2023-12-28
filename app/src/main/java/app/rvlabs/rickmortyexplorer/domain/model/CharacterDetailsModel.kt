package app.rvlabs.rickmortyexplorer.domain.model

data class CharacterDetailsModel(
    val id: String = "0",
    val name: String = "",
    val gender: String = "unknown",
    val origin: String = "",
    val image: String = "",
    val status: String = "",
    val species: String = "",
    val favorite: Boolean = false
)
