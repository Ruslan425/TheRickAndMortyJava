package ru.romazanov.therickandmortyjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dagger.BindsInstance;
import ru.romazanov.screens.character.CharacterListFragment;
import ru.romazanov.screens.episode.EpisodeListFragment;
import ru.romazanov.screens.location.LocationListFragment;
import ru.romazanov.therickandmortyjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    CharacterListFragment characterListFragment = new CharacterListFragment();
    EpisodeListFragment episodeListFragment = new EpisodeListFragment();
    LocationListFragment locationListFragment = new LocationListFragment();

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), characterListFragment).commit();

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.character:
                        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), characterListFragment).commit();
                        return true;
                    case R.id.episode:
                        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), episodeListFragment).commit();
                        return true;
                    case R.id.location:
                        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), locationListFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }
}