package ru.romazanov.data.model.character;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import ru.romazanov.data.model.Info;

public class CharacterAnswer {
    public Info info;
    @SerializedName("results")
    public ArrayList<Character> characters;
}

