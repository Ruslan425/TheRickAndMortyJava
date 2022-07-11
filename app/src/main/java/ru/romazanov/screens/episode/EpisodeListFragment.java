package ru.romazanov.screens.episode;



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
import ru.romazanov.therickandmortyjava.databinding.FragmentEpisodeListBinding;

public class EpisodeListFragment extends Fragment {

    private EpisodeListViewModel mViewModel;

    @Inject
    ViewModelFactory viewModelFactory;


    public static EpisodeListFragment newInstance() {
        return new EpisodeListFragment();
    }

    private FragmentEpisodeListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEpisodeListBinding.inflate(inflater, container, false);

        mViewModel = new ViewModelProvider(this, viewModelFactory).get(EpisodeListViewModel.class);

        binding.textView.setText(mViewModel.getName());

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((App)getActivity().getApplication()).appComponent.injectEpisodeListFragment(this);
    }
}