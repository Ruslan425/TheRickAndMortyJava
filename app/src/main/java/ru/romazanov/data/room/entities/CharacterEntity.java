package ru.romazanov.data.room.entities;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;

import ru.romazanov.data.model.character.Location;
import ru.romazanov.data.model.character.Origin;
import ru.romazanov.data.room.utils.ListTypeConverter;

@Entity(tableName = "character")
public class CharacterEntity {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "character_name1")
    public String name;
    public String status;
    public String species;
    public String type;
    public String gender;
    @Embedded(prefix = "origin_")
    public Origin origin;
    @Embedded(prefix = "location_")
    public Location location;
    public String image;
    @TypeConverters(ListTypeConverter.class)
    public ArrayList<String> episode;
    @ColumnInfo(name = "character_url")
    public String url;
    public String created;

    public CharacterEntity() {
    }

    public CharacterEntity(int id, String name, String status, String species, String type, String gender, Origin origin, Location location, String image, ArrayList<String> episode, String url, String created) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin = origin;
        this.location = location;
        this.image = image;
        this.episode = episode;
        this.url = url;
        this.created = created;
    }
}
