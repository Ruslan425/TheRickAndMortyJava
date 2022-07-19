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
    public String episode;
    @TypeConverters(ListTypeConverter.class)
    public ArrayList<String> characters;
    public String url;
    public String created;

    public EpisodeEntity() {
    }

    public EpisodeEntity(int id, String name, String air_date, String episode, ArrayList<String> characters, String url, String created) {
        this.id = id;
        this.name = name;
        this.air_date = air_date;
        this.episode = episode;
        this.characters = characters;
        this.url = url;
        this.created = created;
    }
}
