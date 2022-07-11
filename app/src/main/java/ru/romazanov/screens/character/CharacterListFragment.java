package ru.romazanov.screens.character;


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
import ru.romazanov.therickandmortyjava.databinding.FragmentCharacterListBinding;

public class CharacterListFragment extends Fragment {


    private FragmentCharacterListBinding binding;
    private CharacterListViewModel mViewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    public static CharacterListFragment newInstance() {
        return new CharacterListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCharacterListBinding.inflate(inflater, container, false);

        mViewModel = new ViewModelProvider(this, viewModelFactory).get(CharacterListViewModel.class);

        binding.text.setText(mViewModel.getName());

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((App)getActivity().getApplication()).appComponent.injectCharacterListFragment(this);
    }
}