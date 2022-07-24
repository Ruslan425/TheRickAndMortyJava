package ru.romazanov.therickandmortyjava;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ru.romazanov.data.Repository;
import ru.romazanov.data.room.entities.MyInterceptorEntity;

public class MainActivityViewModel extends ViewModel {

    Repository repository;

    @Inject
    public MainActivityViewModel(
            Repository repository
    ){
        this.repository = repository;
        list = repository.getMyInterceptorEntityList();
    }

    private final LiveData<List<MyInterceptorEntity>> list;

    public LiveData<List<MyInterceptorEntity>> getList() {
        return list;
    }



}
