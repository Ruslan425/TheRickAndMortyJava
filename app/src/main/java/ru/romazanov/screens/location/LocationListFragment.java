package ru.romazanov.screens.location;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.romazanov.App;
import ru.romazanov.data.model.location.Location;
import ru.romazanov.di.ViewModelFactory;
import ru.romazanov.therickandmortyjava.databinding.FragmentLocationListBinding;

public class LocationListFragment extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private FragmentLocationListBinding binding;
    private LocationAdapter adapter;
    private LiveData<ArrayList<Location>> dataList;
    private LocationListViewModel viewModel;

    public static LocationListFragment newInstance() {
        return new LocationListFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((App) getActivity().getApplication()).getAppComponent().injectLocationListFragment(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLocationListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(LocationListViewModel.class);
        dataList = viewModel.getDataList();

        initRecyclerView();
        getData();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LocationAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (!recyclerView.canScrollVertically(1)) {
                           viewModel.nextPage();
                        }
                    }

                });
    }

    private void getData() {
        dataList.observe(getViewLifecycleOwner(), new Observer<ArrayList<Location>>() {
            @Override
            public void onChanged(ArrayList<Location> locations) {
                adapter.setDataList(locations);
                adapter.notifyDataSetChanged();
            }
        });
    }


}