package com.hookedroid.androidarchitecture.di

import com.hookedroid.androidarchitecture.data.dao.NoteDao
import com.hookedroid.androidarchitecture.data.repository.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepository(noteDao)
    }
}