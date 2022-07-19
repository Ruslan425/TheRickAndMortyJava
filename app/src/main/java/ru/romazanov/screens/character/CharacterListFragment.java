package ru.romazanov.screens.character;


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
import ru.romazanov.data.model.character.Character;
import ru.romazanov.di.ViewModelFactory;
import ru.romazanov.therickandmortyjava.databinding.FragmentCharacterListBinding;

public class CharacterListFragment extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private FragmentCharacterListBinding binding;
    private CharacterAdapter adapter;
    private LiveData<ArrayList<Character>> dataList;
    private CharacterListViewModel viewModel;

    public static CharacterListFragment newInstance() {
        return new CharacterListFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((App) getActivity().getApplication()).getAppComponent().injectCharacterListFragment(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCharacterListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(CharacterListViewModel.class);
        dataList = viewModel.getDataList();

        initRecyclerView();
        getData();

        return binding.getRoot();
    }

    private void initRecyclerView() {

        RecyclerView recyclerView = binding.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CharacterAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (!recyclerView.canScrollVertically(1)) {
                            viewModel.nextPage();
                        }
                    }
                });
    }

    private void getData() {
        dataList.observe(getViewLifecycleOwner(), new Observer<ArrayList<Character>>() {
            @Override
            public void onChanged(ArrayList<Character> characters) {
                adapter.setDataList(characters);
                adapter.notifyDataSetChanged();
            }
        });
    }

}