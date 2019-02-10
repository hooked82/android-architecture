package com.hookedroid.androidarchitecture.di

import com.hookedroid.androidarchitecture.data.dao.CharacterDao
import com.hookedroid.androidarchitecture.data.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(characterDao: CharacterDao): CharacterRepository {
        return CharacterRepository(characterDao)
    }
}