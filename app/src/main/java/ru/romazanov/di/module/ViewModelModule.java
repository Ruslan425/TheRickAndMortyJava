package ru.romazanov.di.module;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import ru.romazanov.data.retrofit.RetrofitApiInterface;
import ru.romazanov.di.ViewModelFactory;
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
    public ViewModel provideCharacterListViewModel(RetrofitApiInterface api) {
        return new CharacterListViewModel(api);
    }

    @Provides
    @IntoMap
    @ViewModelKey(EpisodeListViewModel.class)
    public ViewModel provideEpisodeListViewModel(RetrofitApiInterface api) {
        return new EpisodeListViewModel(api);
    }

    @Provides
    @IntoMap
    @ViewModelKey(LocationListViewModel.class)
    public ViewModel provideLocationListViewModel(RetrofitApiInterface api) {
        return new LocationListViewModel(api);
    }

}
