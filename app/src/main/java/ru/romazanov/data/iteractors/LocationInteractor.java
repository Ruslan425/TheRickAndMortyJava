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
import ru.romazanov.data.model.location.Location;
import ru.romazanov.data.model.location.LocationAnswer;
import ru.romazanov.data.room.entities.LocationEntity;

public class LocationInteractor {

    private final Repository repository;

    private LocationAnswer locationAnswer;

    private final ArrayList<LocationEntity> locationEntities = new ArrayList<>();

    private final MutableLiveData<ArrayList<Location>> locations = new MutableLiveData<>(new ArrayList<>());

    public LiveData<ArrayList<Location>> getLocation() {
        return locations;
    }

    @Inject
    public LocationInteractor(
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
        locationEntities.addAll(getEntityFromEpisode(locationAnswer.locations));
        repository.locationDao.addLocationList(locationEntities);
    }

    void makeLocalCall() {
        locations.postValue(getEpisodeFromEntity(repository.getLocationEntityList()));
    }

    private ArrayList<Location> getEpisodeFromEntity(List<LocationEntity> list) {
        ArrayList<Location> list1 = new ArrayList<>();
        for (LocationEntity locationEntity : list) {
            list1.add( new Location(
                    locationEntity.id,
                    locationEntity.name,
                    locationEntity.type,
                    locationEntity.dimension,
                    locationEntity.residents,
                    locationEntity.url,
                    locationEntity.created
                    ));
        }
        return list1;
    }

    private ArrayList<LocationEntity> getEntityFromEpisode(ArrayList<Location> list) {
        ArrayList<LocationEntity> list1 = new ArrayList<>();
        for (Location location : list) {
            list1.add(new LocationEntity(
                    location.id,
                    location.name,
                    location.type,
                    location.dimension,
                    location.residents,
                    location.url,
                    location.created
            ));
        }
        return list1;
    }
}
