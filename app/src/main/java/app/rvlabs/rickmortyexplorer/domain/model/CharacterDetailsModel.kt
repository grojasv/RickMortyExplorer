package app.rvlabs.rickmortyexplorer.domain.model

import app.rvlabs.rickmortyexplorer.core.Constants.GENDER_UNKNOWN

data class CharacterDetailsModel(
    val id: String = "0",
    val name: String = "",
    val gender: String = GENDER_UNKNOWN,
    val origin: String = "",
    val image: String = "",
    val status: String = "",
    val species: String = "",
    val favorite: Boolean = false
)
