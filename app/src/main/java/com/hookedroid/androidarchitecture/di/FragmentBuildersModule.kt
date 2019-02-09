package com.hookedroid.androidarchitecture.di

import com.hookedroid.androidarchitecture.notes.NotesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeNotesFragment(): NotesFragment
}