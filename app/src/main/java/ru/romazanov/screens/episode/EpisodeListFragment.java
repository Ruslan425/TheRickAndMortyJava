package ru.romazanov.screens.episode;


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
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import ru.romazanov.App;
import ru.romazanov.data.model.character.Character;
import ru.romazanov.data.model.episode.Episode;
import ru.romazanov.di.ViewModelFactory;
import ru.romazanov.screens.character.CharacterAdapter;
import ru.romazanov.therickandmortyjava.databinding.FragmentEpisodeListBinding;

public class EpisodeListFragment extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private FragmentEpisodeListBinding binding;
    private EpisodeAdapter adapter;
    private LiveData<ArrayList<Episode>> dataList;
    private EpisodeListViewModel viewModel;

    public static EpisodeListFragment newInstance() {
        return new EpisodeListFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
       ((App) getActivity().getApplication()).getAppComponent().injectEpisodeListFragment(this);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEpisodeListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(EpisodeListViewModel.class);
        dataList = viewModel.getDataList();

        initRecyclerView();
        getData();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EpisodeAdapter();
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
        dataList.observe(getViewLifecycleOwner(), new Observer<ArrayList<Episode>>() {
            @Override
            public void onChanged(ArrayList<Episode> episodes) {
                adapter.setDataList(episodes);
                adapter.notifyDataSetChanged();
            }
        });
    }


}