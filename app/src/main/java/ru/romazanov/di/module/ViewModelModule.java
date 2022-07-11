package ru.romazanov.di.module;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import ru.romazanov.screens.ViewModelFactory;
import ru.romazanov.screens.character.CharacterListViewModel;
import ru.romazanov.screens.episode.EpisodeListViewModel;
import ru.romazanov.screens.location.LocationListViewModel;

@Module
public class ViewModelModule {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    public ViewModelFactory provideViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(CharacterListViewModel.class)
    public ViewModel provideCharacterListViewModel() {
        return new CharacterListViewModel();
    }

    @Provides
    @IntoMap
    @ViewModelKey(EpisodeListViewModel.class)
    public ViewModel provideEpisodeListViewModel() {
        return new EpisodeListViewModel();
    }

    @Provides
    @IntoMap
    @ViewModelKey(LocationListViewModel.class)
    public ViewModel provideLocationListViewModel() {
        return new LocationListViewModel();
    }

}
