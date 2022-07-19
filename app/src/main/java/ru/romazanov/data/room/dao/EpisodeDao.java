package ru.romazanov.data.room.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;


import ru.romazanov.data.model.episode.Episode;
import ru.romazanov.data.room.entities.EpisodeEntity;

@Dao
public interface EpisodeDao {

    @Insert
    void addEpisodeList(ArrayList<EpisodeEntity> episodes);

    @Update
    void updateEpisodeList(ArrayList<EpisodeEntity> episodes);

    @Insert
    void addEpisode(EpisodeEntity episode);

    @Update
    void updateEpisode(EpisodeEntity episode);

    @Query("SELECT * FROM episode")
    LiveData<List<EpisodeEntity>> getAll();
}
