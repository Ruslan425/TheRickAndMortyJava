package ru.romazanov.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.romazanov.di.module.AppModule;
import ru.romazanov.di.module.NetworkModule;
import ru.romazanov.di.module.ViewModelModule;
import ru.romazanov.screens.character.CharacterListFragment;
import ru.romazanov.screens.episode.EpisodeListFragment;
import ru.romazanov.screens.location.LocationListFragment;
import ru.romazanov.therickandmortyjava.MainActivity;


@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        ViewModelModule.class
})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void injectCharacterListFragment(CharacterListFragment characterListFragment);

    void injectEpisodeListFragment(EpisodeListFragment episodeListFragment);

    void injectLocationListFragment(LocationListFragment locationListFragment);

}
