package ru.romazanov.data.model.location;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import ru.romazanov.data.model.Info;

public class LocationAnswer {
    public Info info;
    @SerializedName("results")
    public ArrayList<Location> locations;
}
