package ru.romazanov.data.iteractors;

import android.os.AsyncTask;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;
import ru.romazanov.data.Repository;
import ru.romazanov.data.model.SearchItem;
import ru.romazanov.data.model.character.Character;
import ru.romazanov.data.model.character.CharacterAnswer;
import ru.romazanov.data.model.episode.Episode;
import ru.romazanov.data.model.episode.EpisodeAnswer;
import ru.romazanov.data.model.location.Location;
import ru.romazanov.data.model.location.LocationAnswer;
import ru.romazanov.data.retrofit.utils.ConverterToSearchItem;

public class SearchInteractor {

    @Inject
    public SearchInteractor(
            Repository repository,
            ConverterToSearchItem converter) {
        this.repository = repository;
        this.converter = converter;
    }

    private final ConverterToSearchItem converter;
    private final Repository repository;
    private CharacterAnswer characterAnswer;
    private EpisodeAnswer episodeAnswer;
    private LocationAnswer locationAnswer;
    private SearchItem searchItemSimple = new SearchItem("no", -1, "no", "no");

    private ArrayList<SearchItem> allList = new ArrayList<>();
    private ArrayList<SearchItem> secondList = new ArrayList<>();

    private MutableLiveData<ArrayList<SearchItem>> searchItemList = new MutableLiveData<>(new ArrayList<>());

    public LiveData<ArrayList<SearchItem>> getSearchItemList() {
        return searchItemList;
    }

    private MutableLiveData<String> error = new MutableLiveData<>();

    public LiveData<String> getError() {
        return error;
    }

    public void clearList() {
        allList.clear();
    }

    private void geAllListFirst(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("name", name);

        allList.addAll(converter.characterToSearchItem(
                getCharacterList(map)
        ));

        allList.addAll(converter.locationToSearchItem(
                getLocationList(map)
        ));

        allList.addAll(converter.episodeToSearchItem(
                getEpisodeList(map)
        ));
       allList.sort(null);
       if (allList.size() == 0) {
           allList.add(searchItemSimple);
       }
       searchItemList.postValue(allList);
    }


    private void nextPage(String name) {
        secondList.clear();
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("name", name);
         if (characterAnswer != null && characterAnswer.info.next != null) {
             String page = characterAnswer.info.next.split("=")[1].split("&")[0];
             map.replace("page", page);
             secondList.addAll(converter.characterToSearchItem(getCharacterList(map)));
         }
         if (locationAnswer != null && locationAnswer.info.next != null){
             String page = locationAnswer.info.next.split("=")[1].split("&")[0];
             map.replace("page", page);
             secondList.addAll(converter.locationToSearchItem(getLocationList(map)));
         }
         if(episodeAnswer != null && episodeAnswer.info.next != null) {
             String page = episodeAnswer.info.next.split("=")[1].split("&")[0];
             map.replace("page", page);
             secondList.addAll(converter.episodeToSearchItem(getEpisodeList(map)));
         }
         secondList.sort(null);
         allList.addAll(secondList);
        if (allList.size() == 0) {
            allList.add(searchItemSimple);
        }
         searchItemList.postValue(allList);
    }


    public void getNextPage(String name) {
        MyAsyncTack2 task = new MyAsyncTack2();
        task.execute(name);
    }
    class MyAsyncTack2 extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            nextPage(strings[0]);
            return null;
        }
    }


    public void makeNetworkCall(String name) {
        MyAsyncTack task = new MyAsyncTack();
        task.execute(name);
    }
    class MyAsyncTack extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            geAllListFirst(strings[0]);
            return null;
        }
    }


    ArrayList<Character> getCharacterList(Map<String, String> map) {
        Call<CharacterAnswer> call = repository.retrofit.getCharacterList(map);
        try {
            Response<CharacterAnswer> response = call.execute();
            characterAnswer = response.body();
        } catch (IOException e) {
            error.postValue(e.getMessage());
        }
        if (characterAnswer == null) {
           // error.postValue("Данных по персонажам нет!");
            return new ArrayList<Character>();
        }
        return characterAnswer.characters;
    }

    ArrayList<Location> getLocationList(Map<String, String> map) {
        Call<LocationAnswer> call = repository.retrofit.getLocationList(map);
        try {
            Response<LocationAnswer> response = call.execute();
            locationAnswer = response.body();
        } catch (IOException e) {
            error.postValue(e.getMessage());
        }
        if (locationAnswer == null) {
           // error.postValue("Данных по локациям нет!");
            return new ArrayList<Location>();
        }
        return locationAnswer.locations;
    }

    ArrayList<Episode> getEpisodeList(Map<String, String> map) {
        Call<EpisodeAnswer> call = repository.retrofit.getEpisodeList(map);
        try {
            Response<EpisodeAnswer> response = call.execute();
            episodeAnswer = response.body();
        } catch (IOException e) {
            error.postValue(e.getMessage());
        }
        if (episodeAnswer == null) {
           // error.postValue("Данных по эпизодам нет!");
            return new ArrayList<Episode>();
        }
        return episodeAnswer.episodes;
    }


}



