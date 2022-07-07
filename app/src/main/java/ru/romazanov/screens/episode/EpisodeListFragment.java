package ru.romazanov.screens.episode;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.romazanov.therickandmortyjava.R;

public class EpisodeListFragment extends Fragment {

    private EpisodeListViewModel mViewModel;

    public static EpisodeListFragment newInstance() {
        return new EpisodeListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_episode_list, container, false);
    }


}