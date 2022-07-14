package ru.romazanov.screens.character;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.romazanov.data.model.character.Character;
import ru.romazanov.data.model.character.CharacterAnswer;
import ru.romazanov.data.retrofit.RetrofitApiInterface;


public class CharacterListViewModel extends ViewModel {




    @Inject
    public CharacterListViewModel(
            RetrofitApiInterface api
    ) {
        this.api = api;
        makeCall(this.map);
    }





    Map<String, String> map = new HashMap<>();
    private RetrofitApiInterface api;
    private CharacterAnswer answer;

    CharacterAnswer getAnswer() {
        return answer;
    }

    private final MutableLiveData<ArrayList<Character>> dataList = new MutableLiveData<>(new ArrayList<Character>());

    LiveData<ArrayList<Character>> getDataList() {
        return dataList;
    }


    public void makeCall(Map<String, String> map) {
        if (map.isEmpty()) map.put("page", "1");
        Call<CharacterAnswer> call = api.getCharacterList(map);
        call.enqueue(new Callback<CharacterAnswer>() {
            @Override
            public void onResponse(Call<CharacterAnswer> call, Response<CharacterAnswer> response) {
                answer = response.body();
                assert answer != null;
                dataList.setValue(answer.characters);
            }

            @Override
            public void onFailure(Call<CharacterAnswer> call, Throwable t) {

            }
        });
    }

    {
    }

}