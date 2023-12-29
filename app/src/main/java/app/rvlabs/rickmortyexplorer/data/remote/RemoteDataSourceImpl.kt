package app.rvlabs.rickmortyexplorer.data.remote

import app.rvlabs.CharactersFilteredByGenderQuery
import app.rvlabs.CharactersQuery
import app.rvlabs.SingleCharacterQuery
import app.rvlabs.rickmortyexplorer.data.mapper.toCharacterDetailsModel
import app.rvlabs.rickmortyexplorer.data.mapper.toCharacterOverviewModel
import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel
import com.apollographql.apollo3.ApolloClient

class RemoteDataSourceImpl(
    private val apolloClient: ApolloClient
) : RemoteDataSource {

    override suspend fun getCharacters(): List<CharacterOverviewModel> {
        return apolloClient
            .query(CharactersQuery())
            .execute()
            .data
            ?.characters
            ?.results?.mapNotNull { it?.toCharacterOverviewModel() }
            ?: emptyList()
    }

    override suspend fun getCharactersFilteredByGender(gender: String): List<CharacterOverviewModel> {
        return apolloClient
            .query(CharactersFilteredByGenderQuery(gender = gender))
            .execute()
            .data
            ?.characters
            ?.results?.mapNotNull { it?.toCharacterOverviewModel() }
            ?: emptyList()
    }

    override suspend fun getCharacterDetails(characterId: String): CharacterDetailsModel {
        return apolloClient
            .query(SingleCharacterQuery(character_id = characterId))
            .execute()
            .data
            ?.character
            ?.toCharacterDetailsModel()
            ?: CharacterDetailsModel()
    }

}



