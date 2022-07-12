package ru.romazanov.screens.location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.romazanov.data.model.location.Location;
import ru.romazanov.data.model.location.LocationAnswer;
import ru.romazanov.data.retrofit.RetrofitApiInterface;

public class LocationListViewModel extends ViewModel {

    @Inject
    public LocationListViewModel(RetrofitApiInterface api) {
        this.api = api;
    }

    private RetrofitApiInterface api;
    private LocationAnswer answer;

    public LocationAnswer getAnswer() {
        return answer;
    }

    private final MutableLiveData<ArrayList<Location>> dataList = new MutableLiveData<>(new ArrayList<>());
    LiveData<ArrayList<Location>> getDataList() {
        return dataList;
    }

    public void makeCall(Map<String, String> map) {
        if (map.isEmpty()) map.put("page", "1");
        Call<LocationAnswer> call = api.getLocationList(map);
        call.enqueue(new Callback<LocationAnswer>() {
            @Override
            public void onResponse(Call<LocationAnswer> call, Response<LocationAnswer> response) {
                answer = response.body();
                assert answer != null;
                dataList.setValue(answer.locations);
            }
            @Override
            public void onFailure(Call<LocationAnswer> call, Throwable t) {

            }
        });
    }

}