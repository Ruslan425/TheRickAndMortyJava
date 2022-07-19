package ru.romazanov.data.model.location;

import java.util.ArrayList;
import java.util.Date;

public class Location {
    public int id;
    public String name;
    public String type;
    public String dimension;
    public ArrayList<String> residents;
    public String url;
    public String created;

    public Location() {
    }

    public Location(int id, String name, String type, String dimension, ArrayList<String> residents, String url, String created) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dimension = dimension;
        this.residents = residents;
        this.url = url;
        this.created = created;
    }
}
