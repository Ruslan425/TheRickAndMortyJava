package ru.romazanov.di.module;


import android.app.Application;


import androidx.annotation.NonNull;
import androidx.room.Room;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.romazanov.data.room.DataBase;
import ru.romazanov.data.room.dao.CharacterDao;
import ru.romazanov.data.room.dao.EpisodeDao;
import ru.romazanov.data.room.dao.LocationDao;

@Module
public class RoomModule {


    private DataBase db;

    public RoomModule(Application application) {
        this.db = Room.databaseBuilder(application, DataBase.class, "database").build();
    }

    @Provides
    @Singleton
    public DataBase getDb() {
        return db;
    }

    @Provides
    @Singleton
    public CharacterDao getCharacterDao(@NonNull DataBase db) {
        return db.getCharacterDao();
    }


    @Provides
    @Singleton
    public EpisodeDao getEpisodeDao(DataBase db) {
        return db.getEpisodeDao();
    }


    @Provides
    @Singleton
    public LocationDao getLocationDao(DataBase db) {
        return db.getLocationDao();
    }

}
