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
import ru.romazanov.data.room.utils.EntityConverter;

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
    public CharacterInteractor provideCharacterInteractor(Repository repository, EntityConverter converter){
        return new CharacterInteractor(repository, converter);
    }

    @Provides
    @Singleton
    public EpisodeInteractor provideEpisodeInteractor(Repository repository, EntityConverter converter){
        return new EpisodeInteractor(repository, converter);
    }

    @Provides
    @Singleton
    public LocationInteractor provideLocationInteractor(
            Repository repository,
            EntityConverter converter
    ){
        return new LocationInteractor(repository, converter);
    }

    @Provides
    @Singleton
    public EntityConverter provideConverter(){
        return new EntityConverter();
    }

}
