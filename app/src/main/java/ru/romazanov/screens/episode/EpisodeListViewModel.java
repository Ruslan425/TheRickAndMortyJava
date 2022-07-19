package ru.romazanov.screens.episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import ru.romazanov.data.iteractors.EpisodeInteractor;
import ru.romazanov.data.model.episode.Episode;

public class EpisodeListViewModel extends ViewModel {

    @Inject
    public EpisodeListViewModel(
            EpisodeInteractor interactor) {
        this.interactor = interactor;
        dataList = interactor.getEpisodes();

    }

    EpisodeInteractor interactor;

    private final LiveData<ArrayList<Episode>> dataList;

    LiveData<ArrayList<Episode>> getDataList() {
        return dataList;
    }


    public void nextPage() {
        if(51 % dataList.getValue().size() != 0) {// Убрать в константу ?
            String page = String.valueOf(dataList.getValue().size() / 20 + 1); // Убрать в констатнут ?
            Map<String, String> map = new HashMap<>();
            map.put("page", page);
            interactor.myAsyncTask(map);
        }
    }
}

