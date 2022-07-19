package ru.romazanov.di.module;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.romazanov.data.Repository;
import ru.romazanov.data.iteractors.CharacterInteractor;
import ru.romazanov.data.retrofit.RetrofitApiInterface;
import ru.romazanov.data.room.DataBase;
import ru.romazanov.data.room.dao.CharacterDao;
import ru.romazanov.data.room.dao.EpisodeDao;
import ru.romazanov.data.room.dao.LocationDao;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    @Singleton
    public CharacterInteractor provideCharacterInteractor(Repository repository){
        return new CharacterInteractor(repository);
    }



}
