package ru.romazanov.data.room.utils;


import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class ListTypeConverter {


    @TypeConverter
    public String fromList(ArrayList<String> hobbies) {
        return String.join(",", hobbies);
    }

    @TypeConverter
    public ArrayList<String> toList(String data) {
        return new ArrayList<>(Arrays.asList(data.split(","))) ;
    }

}
