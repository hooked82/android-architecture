package com.hookedroid.androidarchitecture.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hookedroid.androidarchitecture.notes.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    abstract fun bindNotesViewModel(notesViewModel: NotesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ArchViewModelFactory): ViewModelProvider.Factory
}