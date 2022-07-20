package ru.romazanov.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.romazanov.data.room.dao.CharacterDao;
import ru.romazanov.data.room.dao.EpisodeDao;
import ru.romazanov.data.room.dao.LocationDao;
import ru.romazanov.data.room.dao.MyInterceptorDao;
import ru.romazanov.data.room.entities.CharacterEntity;
import ru.romazanov.data.room.entities.EpisodeEntity;
import ru.romazanov.data.room.entities.LocationEntity;
import ru.romazanov.data.room.entities.MyInterceptorEntity;

@Database(entities = {
        CharacterEntity.class,
        EpisodeEntity.class,
        LocationEntity.class,
        MyInterceptorEntity.class
}, version = 5)
public abstract class DataBase extends RoomDatabase {

    public abstract CharacterDao getCharacterDao();

    public abstract EpisodeDao getEpisodeDao();

    public abstract LocationDao getLocationDao();

    public abstract MyInterceptorDao getMyInterceptorDao();

}


