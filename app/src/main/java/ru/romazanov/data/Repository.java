package ru.romazanov.data;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.romazanov.data.model.character.Character;
import ru.romazanov.data.model.character.CharacterAnswer;
import ru.romazanov.data.retrofit.RetrofitApiInterface;
import ru.romazanov.data.room.DataBase;
import ru.romazanov.data.room.dao.CharacterDao;
import ru.romazanov.data.room.dao.EpisodeDao;
import ru.romazanov.data.room.dao.LocationDao;
import ru.romazanov.data.room.dao.MyInterceptorDao;
import ru.romazanov.data.room.entities.CharacterEntity;
import ru.romazanov.data.room.entities.EpisodeEntity;
import ru.romazanov.data.room.entities.LocationEntity;
import ru.romazanov.data.room.entities.MyInterceptorEntity;

public class Repository {

    public CharacterDao characterDao;
    public EpisodeDao episodeDao;
    public LocationDao locationDao;
    public MyInterceptorDao myInterceptorDao;

    public RetrofitApiInterface retrofit;


    @Inject
    public Repository(
            DataBase db,
            RetrofitApiInterface retrofit
    ) {
        this.characterDao = db.getCharacterDao();
        this.episodeDao = db.getEpisodeDao();
        this.locationDao = db.getLocationDao();
        this.retrofit = retrofit;
        this.myInterceptorDao = db.getMyInterceptorDao();
    }

    public List<CharacterEntity> getCharacterEntityList() {
        return characterDao.getAll();
    }

    public List<EpisodeEntity> getEpisodeEntityList() {
        return episodeDao.getAll();
    }

    public List<LocationEntity> getLocationEntityList() {
        return locationDao.getAll();
    }

    public LiveData<List<MyInterceptorEntity>> getMyInterceptorEntityList() {
        return myInterceptorDao.getAll();
    }

}
    


