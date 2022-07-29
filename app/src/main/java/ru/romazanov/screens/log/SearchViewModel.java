package ru.romazanov.screens.log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.romazanov.data.iteractors.SearchInteractor;
import ru.romazanov.data.model.SearchItem;

public class SearchViewModel extends ViewModel {


    @Inject
    public SearchViewModel(
            SearchInteractor interactor
    ) {
        this.interactor = interactor;
        dataList = interactor.getSearchItemList();
        error = interactor.getError();
    }

    private SearchInteractor interactor;

    private LiveData<ArrayList<SearchItem>> dataList;
    public LiveData<ArrayList<SearchItem>> getDataList() {
        return dataList;
    }

    private LiveData<String> error;
    public LiveData<String> getError() {
        return error;
    }

    public void makeCall(String name) {
        interactor.makeNetworkCall(name);
    }

    public void nextPage(String name) {interactor.getNextPage(name);}

    public void clearList(){interactor.clearList();}
}