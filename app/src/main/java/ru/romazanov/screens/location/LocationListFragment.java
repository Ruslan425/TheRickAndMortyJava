package ru.romazanov.screens.location;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.romazanov.App;
import ru.romazanov.screens.ViewModelFactory;
import ru.romazanov.therickandmortyjava.databinding.FragmentLocationListBinding;

public class LocationListFragment extends Fragment {

    private LocationListViewModel mViewModel;
    private FragmentLocationListBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;

    public static LocationListFragment newInstance() {
        return new LocationListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLocationListBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this, viewModelFactory).get(LocationListViewModel.class);

        binding.textView.setText(mViewModel.getName());

        return binding.getRoot();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((App)getActivity().getApplication()).appComponent.injectLocationListFragment(this);
    }
}