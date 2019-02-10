package com.hookedroid.androidarchitecture.di

import com.hookedroid.androidarchitecture.character.CharactersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCharactersFragment(): CharactersFragment
}