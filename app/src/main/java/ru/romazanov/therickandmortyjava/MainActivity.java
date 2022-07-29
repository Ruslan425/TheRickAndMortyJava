package ru.romazanov.therickandmortyjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import javax.inject.Inject;

import ru.romazanov.App;
import ru.romazanov.data.room.entities.MyInterceptorEntity;
import ru.romazanov.di.ViewModelFactory;
import ru.romazanov.screens.character.CharacterListFragment;
import ru.romazanov.screens.episode.EpisodeListFragment;
import ru.romazanov.screens.location.LocationListFragment;
import ru.romazanov.screens.log.SearchFragment;
import ru.romazanov.therickandmortyjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityAdapter adapter;
    private LiveData<List<MyInterceptorEntity>> dataList;
    MainActivityViewModel viewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        ((App) getApplication()).getAppComponent().inject(this);

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
                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), SearchFragment.newInstance()).commit();
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.button_in_toolba, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        binding.container.openDrawer(GravityCompat.END);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel.class);
        dataList = viewModel.getList();
        initRecyclerView();
        getData();
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.logList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainActivityAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        dataList.observe(this, new Observer<List<MyInterceptorEntity>>() {
            @Override
            public void onChanged(List<MyInterceptorEntity> myInterceptorEntities) {
                adapter.setList(myInterceptorEntities);
                adapter.notifyDataSetChanged();
            }
        });
    }

}