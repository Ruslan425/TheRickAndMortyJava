package ru.romazanov.di.module;

import android.os.Build;

import androidx.annotation.RequiresApi;
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
import ru.romazanov.data.Repository;
import ru.romazanov.data.iteractors.CharacterInteractor;
import ru.romazanov.data.iteractors.EpisodeInteractor;
import ru.romazanov.data.iteractors.LocationInteractor;
import ru.romazanov.data.iteractors.SearchInteractor;
import ru.romazanov.data.retrofit.RetrofitApiInterface;
import ru.romazanov.di.ViewModelFactory;
import ru.romazanov.screens.character.CharacterListViewModel;
import ru.romazanov.screens.episode.EpisodeListViewModel;
import ru.romazanov.screens.location.LocationListViewModel;
import ru.romazanov.screens.log.SearchViewModel;
import ru.romazanov.therickandmortyjava.MainActivityViewModel;


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
    public ViewModel provideCharacterListViewModel(CharacterInteractor interactor) {
        return new CharacterListViewModel(interactor);
    }

    @Provides
    @IntoMap
    @ViewModelKey(EpisodeListViewModel.class)
    public ViewModel provideEpisodeListViewModel(EpisodeInteractor interactor) {
        return new EpisodeListViewModel(interactor);
    }

    @Provides
    @IntoMap
    @ViewModelKey(LocationListViewModel.class)
    public ViewModel provideLocationListViewModel(LocationInteractor interactor) {
        return new LocationListViewModel(interactor);
    }


    @Provides
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    public ViewModel provideMainActivityViewModel(Repository repository) {
        return new MainActivityViewModel(repository);
    }

    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    public ViewModel provideSearchViewModel(
            SearchInteractor interactor
    ) {
        return new SearchViewModel(interactor);
    }

}
