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
import ru.romazanov.data.model.character.Character;
import ru.romazanov.data.model.character.CharacterAnswer;
import ru.romazanov.data.room.entities.CharacterEntity;
import ru.romazanov.data.room.utils.EntityConverter;

public class CharacterInteractor {

    private final Repository repository;

    private CharacterAnswer characterAnswer;

    private final ArrayList<CharacterEntity> characterEntities = new ArrayList<>();

    private final MutableLiveData<ArrayList<Character>> characters = new MutableLiveData<>(new ArrayList<>());

    public LiveData<ArrayList<Character>> getCharacters() {
        return characters;
    }

    private final EntityConverter converter;

    @Inject
    public CharacterInteractor(
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
            if (repository.characterDao.getAll().isEmpty()) {
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
        Call<CharacterAnswer> call = repository.retrofit.getCharacterList(map);
        try {
            Response<CharacterAnswer> response = call.execute();
            characterAnswer = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert characterAnswer != null;
        ArrayList<Character> newList = new ArrayList<>(Objects.requireNonNull(characters.getValue()));
        newList.addAll(characterAnswer.characters);
        characters.postValue(newList);
        characterEntities.addAll(converter.getEntityFromCharacter(characterAnswer.characters));
        repository.characterDao.addCharacterList(characterEntities);
    }

    void makeLocalCall() {
        characters.postValue(converter.getCharacterFromEntity(repository.getCharacterEntityList()));
    }


}
