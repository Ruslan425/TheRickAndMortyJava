package ru.romazanov.data.iteractors;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;
import ru.romazanov.data.Repository;
import ru.romazanov.data.model.episode.Episode;
import ru.romazanov.data.model.episode.EpisodeAnswer;
import ru.romazanov.data.room.entities.EpisodeEntity;
import ru.romazanov.data.room.utils.EntityConverter;

public class EpisodeInteractor {

    private final Repository repository;

    private EpisodeAnswer episodeAnswer;



    private final ArrayList<EpisodeEntity> episodeEntities = new ArrayList<>();

    public ArrayList<EpisodeEntity> getEpisodeEntities() {
        return episodeEntities;
    }

    private final MutableLiveData<ArrayList<Episode>> episodes = new MutableLiveData<>(new ArrayList<>());

    public LiveData<ArrayList<Episode>> getEpisodes() {
        return episodes;
    }

    private EntityConverter converter;



    @Inject
    public EpisodeInteractor(
            Repository repository,
            EntityConverter converter
    ) {
        this.repository = repository;
        this.converter = converter;
        initTask();
    }

    private void initTask() {
        new InitTask().execute();
    }

    class InitTask extends AsyncTask<Void, Void, Void> {
        @Override
        public Void doInBackground(Void... voids) {
            if (repository.episodeDao.getAll().isEmpty()) {
                makeNetworkCall(new HashMap<>());
            } else {
                makeLocalCall();
            }
            return null;
        }
    }

    public void myAsyncTask(Map<String, String> map) {
        new MyAsyncTask().execute(map);
    }

    class MyAsyncTask extends AsyncTask<Map<String, String>, Void, Void> {
        @Override
        protected Void doInBackground(Map<String, String>... maps) {
            makeNetworkCall(maps[0]);
            return null;
        }
    }

    void makeNetworkCall(Map<String, String> map) {
        if (map.isEmpty()) map.put("page", "1");
        Call<EpisodeAnswer> call = repository.retrofit.getEpisodeList(map);
        try {
            Response<EpisodeAnswer> response = call.execute();
            episodeAnswer = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert episodeAnswer != null;
        ArrayList<Episode> newList = new ArrayList<>(Objects.requireNonNull(episodes.getValue()));
        newList.addAll(episodeAnswer.episodes);
        episodes.postValue(newList);
        episodeEntities.addAll(converter.getEntityFromEpisode(episodeAnswer.episodes));
        repository.episodeDao.addEpisodeList(episodeEntities);
    }

    void makeLocalCall() {
        episodes.postValue(converter.getEpisodeFromEntity(repository.getEpisodeEntityList()));
    }


}
