package com.hookedroid.androidarchitecture.di

import com.hookedroid.androidarchitecture.api.CharacterApi
import com.hookedroid.androidarchitecture.data.db.ArchDb
import com.hookedroid.androidarchitecture.data.repository.CharacterRepository
import com.hookedroid.androidarchitecture.util.AppExecutors
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(db: ArchDb, characterApi: CharacterApi, appExecutors: AppExecutors): CharacterRepository {
        return CharacterRepository(db, characterApi, appExecutors)
    }
}