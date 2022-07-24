package ru.romazanov.data.room.entities;

import androidx.room.Entity;

import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;

import ru.romazanov.data.room.utils.ListTypeConverter;

@Entity(tableName = "location")
public class LocationEntity {
    @PrimaryKey
    public int id;
    public String name;
    public String type;
    public String dimension;
    @TypeConverters(ListTypeConverter.class)
    public ArrayList<String> residents;
    public String url;
    public String created;

    public LocationEntity() {
    }

    public LocationEntity(int id, String name, String type, String dimension, ArrayList<String> residents, String url, String created) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dimension = dimension;
        this.residents = residents;
        this.url = url;
        this.created = created;
    }
}
