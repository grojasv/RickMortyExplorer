package app.rvlabs.rickmortyexplorer.core.di

import android.content.Context
import androidx.room.Room
import app.rvlabs.rickmortyexplorer.core.Constants
import app.rvlabs.rickmortyexplorer.core.Constants.REMOTE_URL
import app.rvlabs.rickmortyexplorer.data.RickMortyRepositoryImpl
import app.rvlabs.rickmortyexplorer.data.local.LocalDataSource
import app.rvlabs.rickmortyexplorer.data.local.LocalDataSourceImpl
import app.rvlabs.rickmortyexplorer.data.local.db.AppDatabase
import app.rvlabs.rickmortyexplorer.data.local.db.FavoriteCharacterDao
import app.rvlabs.rickmortyexplorer.data.remote.RemoteDataSource
import app.rvlabs.rickmortyexplorer.data.remote.RemoteDataSourceImpl
import app.rvlabs.rickmortyexplorer.domain.RickMortyRepository
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharacterDetailsUseCase
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharactersFilteredByGenderUseCase
import app.rvlabs.rickmortyexplorer.domain.usecase.GetCharactersUseCase
import app.rvlabs.rickmortyexplorer.domain.usecase.IsFavoriteCharacterSavedUseCase
import app.rvlabs.rickmortyexplorer.domain.usecase.RemoveFavoriteCharacterUseCase
import app.rvlabs.rickmortyexplorer.domain.usecase.SetFavoriteCharacterUseCase
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room
            .databaseBuilder(
                appContext,
                AppDatabase::class.java,
                Constants.DB_NAME
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(appDatabase: AppDatabase): FavoriteCharacterDao {
        return appDatabase.dao
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apolloClient: ApolloClient): RemoteDataSource {
        return RemoteDataSourceImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(favoriteCharacterDao: FavoriteCharacterDao): LocalDataSource {
        return LocalDataSourceImpl(favoriteCharacterDao)
    }

    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): RickMortyRepository {
        return RickMortyRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(rickMortyRepository: RickMortyRepository): GetCharactersUseCase {
        return GetCharactersUseCase(rickMortyRepository)
    }

    @Provides
    @Singleton
    fun provideGetCharactersFilteredByGenderUseCase(rickMortyRepository: RickMortyRepository): GetCharactersFilteredByGenderUseCase {
        return GetCharactersFilteredByGenderUseCase(rickMortyRepository)
    }

    @Provides
    @Singleton
    fun provideGetCharacterDetailsUseCase(rickMortyRepository: RickMortyRepository): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(rickMortyRepository)
    }

    @Provides
    @Singleton
    fun provideRemoveFavoriteCharacterUseCase(rickMortyRepository: RickMortyRepository): RemoveFavoriteCharacterUseCase {
        return RemoveFavoriteCharacterUseCase(rickMortyRepository)
    }

    @Provides
    @Singleton
    fun provideSetFavoriteCharacterUseCase(rickMortyRepository: RickMortyRepository): SetFavoriteCharacterUseCase {
        return SetFavoriteCharacterUseCase(rickMortyRepository)
    }

    @Provides
    @Singleton
    fun provideIsFavoriteCharacterSavedUseCase(rickMortyRepository: RickMortyRepository): IsFavoriteCharacterSavedUseCase {
        return IsFavoriteCharacterSavedUseCase(rickMortyRepository)
    }
}