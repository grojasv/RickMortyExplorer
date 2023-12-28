package app.rvlabs.rickmortyexplorer.core.di

import app.rvlabs.rickmortyexplorer.core.Constants.REMOTE_URL
import app.rvlabs.rickmortyexplorer.data.remote.RemoteDataSource
import app.rvlabs.rickmortyexplorer.domain.RickMortyRepository
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharacterDetailsUseCase
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharactersUseCase
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(REMOTE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideRickMortyRepository(apolloClient: ApolloClient): RickMortyRepository {
        return RemoteDataSource(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(rickMortyRepository: RickMortyRepository): GetCharactersUseCase {
        return GetCharactersUseCase(rickMortyRepository)
    }

    @Provides
    @Singleton
    fun provideGetCharacterDetailsUseCase(rickMortyRepository: RickMortyRepository): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(rickMortyRepository)
    }

}