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

    @Inject
    public EpisodeInteractor(
            Repository repository
    ) {
        this.repository = repository;
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
        episodeEntities.addAll(getEntityFromEpisode(episodeAnswer.episodes));
        repository.episodeDao.addEpisodeList(episodeEntities);
    }

    void makeLocalCall() {
        episodes.postValue(getEpisodeFromEntity(repository.getEpisodeEntityList()));
    }

    private ArrayList<Episode> getEpisodeFromEntity(List<EpisodeEntity> list) {
        ArrayList<Episode> list1 = new ArrayList<>();
        for (EpisodeEntity episodeEntity : list) {
            list1.add(new Episode(
                    episodeEntity.id,
                    episodeEntity.name,
                    episodeEntity.air_date,
                    episodeEntity.episode,
                    episodeEntity.characters,
                    episodeEntity.url,
                    episodeEntity.created
            ));
        }
        return list1;
    }

    private ArrayList<EpisodeEntity> getEntityFromEpisode(ArrayList<Episode> list) {
        ArrayList<EpisodeEntity> list1 = new ArrayList<>();
        for (Episode episode : list) {
            list1.add(new EpisodeEntity(
                    episode.id,
                    episode.name,
                    episode.air_date,
                    episode.episode,
                    episode.characters,
                    episode.url,
                    episode.created
            ));
        }
        return list1;
    }
}
