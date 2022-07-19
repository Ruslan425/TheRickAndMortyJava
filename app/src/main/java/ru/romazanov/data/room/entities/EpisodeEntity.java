package ru.romazanov.data.room.entities;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;

import ru.romazanov.data.room.utils.ListTypeConverter;

@Entity(tableName = "episode")
public class EpisodeEntity {
    @PrimaryKey
    public int id;
    public String name;
    public String air_date;
    @Embedded(prefix = "episode")
    public String episode;
    @TypeConverters(ListTypeConverter.class)
    public ArrayList<String> characters;
    public String url;
    public String created;

}
