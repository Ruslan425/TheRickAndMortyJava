package ru.romazanov.data.model.episode;

import java.util.ArrayList;
import java.util.Date;

public class Episode {
    public int id;
    public String name;
    public String air_date;
    public String episode;
    public ArrayList<String> characters;
    public String url;
    public String created;

    public Episode() {
    }

    public Episode(int id, String name, String air_date, String episode, ArrayList<String> characters, String url, String created) {
        this.id = id;
        this.name = name;
        this.air_date = air_date;
        this.episode = episode;
        this.characters = characters;
        this.url = url;
        this.created = created;
    }
}
