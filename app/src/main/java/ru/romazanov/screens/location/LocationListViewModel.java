package ru.romazanov.screens.location;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import ru.romazanov.data.iteractors.LocationInteractor;
import ru.romazanov.data.model.location.Location;

public class LocationListViewModel extends ViewModel {

    @Inject
    public LocationListViewModel(LocationInteractor interactor) {
        this.interactor = interactor;
        dataList = interactor.getLocation();
    }

    LocationInteractor interactor;

    private final LiveData<ArrayList<Location>> dataList;

    LiveData<ArrayList<Location>> getDataList() {
        return dataList;
    }


    public void nextPage() {
        if(dataList.getValue().size() % 20  == 0) {
            String page = String.valueOf(dataList.getValue().size() / 20 + 1);
            Map<String, String> map = new HashMap<>();
            map.put("page", page);
            interactor.myAsyncTask(map);
        }
    }
}