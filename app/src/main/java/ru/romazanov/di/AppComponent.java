package ru.romazanov.di;



import androidx.fragment.app.Fragment;

import dagger.Component;
import ru.romazanov.di.module.AppModule;
import ru.romazanov.screens.character.CharacterListFragment;
import ru.romazanov.screens.episode.EpisodeListFragment;
import ru.romazanov.screens.location.LocationListFragment;
import ru.romazanov.therickandmortyjava.MainActivity;

@Component(modules = {
        AppModule.class,
})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void injectCharacterListFragment(CharacterListFragment characterListFragment);

    void injectEpisodeListFragment(EpisodeListFragment episodeListFragment);

    void injectLocationListFragment(LocationListFragment locationListFragment);

}
