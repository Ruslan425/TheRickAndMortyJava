package ru.romazanov.screens.episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.romazanov.data.model.episode.Episode;
import ru.romazanov.data.model.episode.EpisodeAnswer;
import ru.romazanov.data.retrofit.RetrofitApiInterface;

public class EpisodeListViewModel extends ViewModel {

    @Inject
    public EpisodeListViewModel(RetrofitApiInterface api) {
        this.api = api;
    }

    private RetrofitApiInterface api;
    private EpisodeAnswer answer;

    public EpisodeAnswer getAnswer() {
        return answer;
    }

    private final MutableLiveData<ArrayList<Episode>> dataList = new MutableLiveData<>(new ArrayList<Episode>());

    LiveData<ArrayList<Episode>> getDataList() {
        return dataList;
    }

    public void makeCall(Map<String, String> map) {
        if (map.isEmpty()) map.put("page", "1");
        Call<EpisodeAnswer> call = api.getEpisodeList(map);
        call.enqueue(new Callback<EpisodeAnswer>() {
            @Override
            public void onResponse(Call<EpisodeAnswer> call, Response<EpisodeAnswer> response) {
                answer = response.body();
                assert answer != null;
                dataList.setValue(answer.episodes);
            }

            @Override
            public void onFailure(Call<EpisodeAnswer> call, Throwable t) {

            }
        });
    }


}