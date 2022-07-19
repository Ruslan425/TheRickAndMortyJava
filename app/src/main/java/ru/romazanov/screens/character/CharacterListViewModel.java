package ru.romazanov.screens.character;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import ru.romazanov.data.iteractors.CharacterInteractor;
import ru.romazanov.data.model.character.Character;


public class CharacterListViewModel extends ViewModel {

    @Inject
    public CharacterListViewModel(
            CharacterInteractor interactor
    ) {
        this.interactor = interactor;
        dataList = interactor.getCharacters();

    }
    private CharacterInteractor interactor;

    private final LiveData<ArrayList<Character>> dataList;

    LiveData<ArrayList<Character>> getDataList() {
        return dataList;
    }


    void nextPage() {
        if(826 % dataList.getValue().size() != 0) { // Убрать в константу ?
            String page = String.valueOf(dataList.getValue().size() / 20 + 1); // Убрать в констатнут ?
            Map<String, String> map = new HashMap<>();
            map.put("page", page);
            interactor.myAsyncTask(map);
        }
    }

}