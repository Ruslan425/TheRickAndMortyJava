package ru.romazanov.screens.location;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.romazanov.therickandmortyjava.databinding.FragmentLocationListBinding;

public class LocationListFragment extends Fragment {

    private LocationListViewModel mViewModel;

    public static LocationListFragment newInstance() {
        return new LocationListFragment();
    }

    private FragmentLocationListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLocationListBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

}