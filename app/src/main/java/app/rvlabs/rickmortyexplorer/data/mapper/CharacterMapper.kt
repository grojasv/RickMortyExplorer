package app.rvlabs.rickmortyexplorer.data.mapper

import app.rvlabs.CharactersQuery
import app.rvlabs.SingleCharacterQuery
import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel

fun CharactersQuery.Result.toCharacterOverviewModel(): CharacterOverviewModel {
    return CharacterOverviewModel(
        id = id ?: "0",
        name = name ?: "",
        gender = gender ?: "unknown",
        origin = origin?.name ?: "",
        image = image ?: ""
    )
}

fun SingleCharacterQuery.Character.toCharacterDetailsModel(): CharacterDetailsModel {
    return CharacterDetailsModel(
        id = id ?: "0",
        name = name ?: "",
        gender = gender ?: "unknown",
        origin = origin?.name ?: "",
        image = image ?: "",
        status = status ?: "",
        species = species ?: ""
    )
}