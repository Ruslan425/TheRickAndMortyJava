package ru.romazanov.data.iteractors;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;
import ru.romazanov.data.Repository;
import ru.romazanov.data.model.location.Location;
import ru.romazanov.data.model.location.LocationAnswer;
import ru.romazanov.data.room.entities.LocationEntity;
import ru.romazanov.data.room.utils.EntityConverter;

public class LocationInteractor {

    private final Repository repository;

    private LocationAnswer locationAnswer;

    private final ArrayList<LocationEntity> locationEntities = new ArrayList<>();

    private final MutableLiveData<ArrayList<Location>> locations = new MutableLiveData<>(new ArrayList<>());

    private final EntityConverter converter;


    public LiveData<ArrayList<Location>> getLocation() {
        return locations;
    }

    @Inject
    public LocationInteractor(
            Repository repository,
            EntityConverter converter
    ) {
        this.converter = converter;
        this.repository = repository;
        initTask();
    }

    private void initTask() {
        new InitTask().execute();
    }

    class InitTask extends AsyncTask<Void, Void, Void> {
        @Override
        public Void doInBackground(Void... voids) {
            if (repository.locationDao.getAll().isEmpty()) {
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
        Call<LocationAnswer> call = repository.retrofit.getLocationList(map);
        try {
            Response<LocationAnswer> response = call.execute();
            locationAnswer = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert locationAnswer != null;
        ArrayList<Location> newList = new ArrayList<>(Objects.requireNonNull(locations.getValue()));
        newList.addAll(locationAnswer.locations);
        locations.postValue(newList);
        locationEntities.addAll(converter.getEntityFromLocation(locationAnswer.locations));
        repository.locationDao.addLocationList(locationEntities);
    }

    void makeLocalCall() {
        locations.postValue(converter.getLocationFromEntity(repository.getLocationEntityList()));
    }

}
