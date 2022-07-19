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
}
