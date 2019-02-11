package com.hookedroid.androidarchitecture.di

import com.hookedroid.androidarchitecture.character.CharactersFragment
import com.hookedroid.androidarchitecture.episode.EpisodesFragment
import com.hookedroid.androidarchitecture.location.LocationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCharactersFragment(): CharactersFragment

    @ContributesAndroidInjector
    abstract fun contributeLocationsFragment(): LocationsFragment

    @ContributesAndroidInjector
    abstract fun contributeEpisodesFragment(): EpisodesFragment
}