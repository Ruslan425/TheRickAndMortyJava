package ru.romazanov.therickandmortyjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationBarView;

import javax.inject.Inject;

import ru.romazanov.App;
import ru.romazanov.screens.character.CharacterListFragment;
import ru.romazanov.screens.episode.EpisodeListFragment;
import ru.romazanov.screens.location.LocationListFragment;
import ru.romazanov.therickandmortyjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        ((App)getApplication()).getAppComponent().inject(this);

        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), CharacterListFragment.newInstance()).commit();

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.character:
                        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), CharacterListFragment.newInstance()).commit();
                        return true;
                    case R.id.episode:
                        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), EpisodeListFragment.newInstance()).commit();
                        return true;
                    case R.id.location:
                        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), LocationListFragment.newInstance()).commit();
                        return true;
                }
                return false;
            }
        });

    }
}