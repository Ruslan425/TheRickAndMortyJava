package ru.romazanov.screens.log;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.romazanov.App;
import ru.romazanov.data.model.SearchItem;
import ru.romazanov.di.ViewModelFactory;
import ru.romazanov.therickandmortyjava.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private FragmentSearchBinding binding;
    private SearchAdapter adapter;
    private LiveData<ArrayList<SearchItem>> dataList;
    private LiveData<String> error;
    private SearchViewModel viewModel;
    private String name;


    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((App) getActivity().getApplication()).getAppComponent().injectSearchFragment(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this,viewModelFactory).get(SearchViewModel.class);
        dataList = viewModel.getDataList();
        error = viewModel.getError();
        binding.searchView.setOnQueryTextListener(new MyListener());
        initRecyclerView();
        getData();
        catchError();

        return binding.getRoot();
    }

    class MyListener implements SearchView.OnQueryTextListener{

        String last = "";

        @Override
        public boolean onQueryTextSubmit(String query) {
            viewModel.clearList();
            viewModel.makeCall(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (newText.length() > last.length()) {
                viewModel.clearList();
                viewModel.makeCall(newText);
                last = newText;
            } else {
                last = newText;
            }
            name = newText;
            return false;
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new SearchAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (!recyclerView.canScrollVertically(1)) {
                            viewModel.nextPage(name);
                        }
                    }
                });
    }

    private  void getData() {
        dataList.observe(getViewLifecycleOwner(), searchItems -> {
            adapter.setDataList(searchItems);
            adapter.notifyDataSetChanged();
        });
    }

    private void catchError() {
        error.observe(getViewLifecycleOwner(), error -> {
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                });
    }

}