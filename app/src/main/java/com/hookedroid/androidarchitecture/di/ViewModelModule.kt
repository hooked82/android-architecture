package com.hookedroid.androidarchitecture.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hookedroid.androidarchitecture.character.CharacterDetailsViewModel
import com.hookedroid.androidarchitecture.character.CharactersViewModel
import com.hookedroid.androidarchitecture.episode.EpisodesViewModel
import com.hookedroid.androidarchitecture.location.LocationsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindCharactersViewModel(charactersViewModel: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailsViewModel::class)
    abstract fun bindCharacterDetailsViewModel(characterDetailsViewModel: CharacterDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationsViewModel::class)
    abstract fun bindLocationsViewModel(locationsViewModel: LocationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EpisodesViewModel::class)
    abstract fun bindEpisodesViewModel(episodesViewModel: EpisodesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ArchViewModelFactory): ViewModelProvider.Factory
}