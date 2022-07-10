package ru.romazanov.screens.episode;



import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.romazanov.therickandmortyjava.databinding.FragmentEpisodeListBinding;

public class EpisodeListFragment extends Fragment {

    private EpisodeListViewModel mViewModel;

    public static EpisodeListFragment newInstance() {
        return new EpisodeListFragment();
    }

    private FragmentEpisodeListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEpisodeListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


}