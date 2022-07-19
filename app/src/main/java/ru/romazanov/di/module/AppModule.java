package ru.romazanov.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.romazanov.data.Repository;
import ru.romazanov.data.iteractors.CharacterInteractor;
import ru.romazanov.data.iteractors.EpisodeInteractor;
import ru.romazanov.data.iteractors.LocationInteractor;
import ru.romazanov.data.retrofit.RetrofitApiInterface;
import ru.romazanov.data.room.DataBase;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }


    @Provides
    @Singleton
    public Repository provideRepository(DataBase db, RetrofitApiInterface retrofit){
        return new Repository(db, retrofit);
    }


    @Provides
    @Singleton
    public CharacterInteractor provideCharacterInteractor(Repository repository){
        return new CharacterInteractor(repository);
    }

    @Provides
    @Singleton
    public EpisodeInteractor provideEpisodeInteractor(Repository repository){
        return new EpisodeInteractor(repository);
    }

    @Provides
    @Singleton
    public LocationInteractor provideLocationInteractor(Repository repository){
        return new LocationInteractor(repository);
    }

}
