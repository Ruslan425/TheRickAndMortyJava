package ru.romazanov.data.model.episode;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import ru.romazanov.data.model.Info;

public class EpisodeAnswer {
    public Info info;
    @SerializedName("results")
    public ArrayList<Episode> episodes;
}
