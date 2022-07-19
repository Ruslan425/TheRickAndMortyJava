package ru.romazanov.data.room.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import ru.romazanov.data.model.location.Location;
import ru.romazanov.data.room.entities.LocationEntity;

@Dao
public interface LocationDao {

    @Insert
    void addLocationList(ArrayList<LocationEntity> locations);

    @Update
    void updateLocationList(ArrayList<LocationEntity> locations);

    @Insert
    void addLocation(LocationEntity location);

    @Update
    void updateLocation(LocationEntity location);

    @Query("SELECT * FROM location")
    LiveData<List<LocationEntity>> getAll();
}
