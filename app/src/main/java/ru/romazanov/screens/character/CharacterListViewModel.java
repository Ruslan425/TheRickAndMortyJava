package ru.romazanov.screens.character;


import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import ru.romazanov.data.iteractors.CharacterInteractor;
import ru.romazanov.data.model.character.Character;
import ru.romazanov.data.model.character.CharacterAnswer;
import ru.romazanov.data.retrofit.RetrofitApiInterface;


public class CharacterListViewModel extends ViewModel {

    @Inject
    public CharacterListViewModel(
            CharacterInteractor interactor
    ) {
        this.interactor = interactor;
        dataList = interactor.getCharacters();

    }

    private RetrofitApiInterface api;

    private final LiveData<ArrayList<Character>> dataList;

    LiveData<ArrayList<Character>> getDataList() {
        return dataList;
    }

    private CharacterInteractor interactor;


    void nextPage() {
        if(826 / Objects.requireNonNull(dataList.getValue()).size() != 0) { // Убрать в константу ?
            String page = String.valueOf(dataList.getValue().size() / 20 + 1); // Убрать в констатнут ?
            Map<String, String> map = new HashMap<>();
            map.put("page", page);
            interactor.myAsyncTask(map);
        }
    }

}