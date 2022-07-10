package ru.romazanov.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ru.romazanov.screens.ViewModelFactory;
import ru.romazanov.screens.ViewModelKey;
import ru.romazanov.screens.character.CharacterListViewModel;
import ru.romazanov.screens.episode.EpisodeListViewModel;
import ru.romazanov.screens.location.LocationListViewModel;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel.class)
    abstract ViewModel provideCharacterListViewModel(CharacterListViewModel characterListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(EpisodeListViewModel.class)
    abstract ViewModel provideEpisodeListViewModel(EpisodeListViewModel episodeListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LocationListViewModel.class)
    abstract ViewModel provideLocationListViewModel(LocationListViewModel locationListViewModel);

    @Binds
    abstract ViewModelProvider.Factory provideViewModelFactory(ViewModelFactory factory);
}
