package app.rvlabs.rickmortyexplorer.data.remote

import app.rvlabs.CharactersQuery
import app.rvlabs.SingleCharacterQuery
import app.rvlabs.rickmortyexplorer.data.mapper.toCharacterDetailsModel
import app.rvlabs.rickmortyexplorer.data.mapper.toCharacterOverviewModel
import app.rvlabs.rickmortyexplorer.domain.RickMortyRepository
import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel
import com.apollographql.apollo3.ApolloClient

class RemoteDataSource(
    private val apolloClient: ApolloClient
) : RickMortyRepository {

    override suspend fun getCharacters(): List<CharacterOverviewModel> {
        return apolloClient
            .query(CharactersQuery())
            .execute()
            .data
            ?.characters
            ?.results?.mapNotNull { it?.toCharacterOverviewModel() }
            ?: emptyList()
    }

    override suspend fun getCharacterDetails(id: String): CharacterDetailsModel {
        return apolloClient
            .query(SingleCharacterQuery(character_id = id))
            .execute()
            .data
            ?.character
            ?.toCharacterDetailsModel()
            ?: CharacterDetailsModel()
    }

}



