package ru.romazanov.data.retrofit.utils;



import java.util.ArrayList;

import ru.romazanov.data.model.SearchItem;
import ru.romazanov.data.model.character.Character;
import ru.romazanov.data.model.episode.Episode;
import ru.romazanov.data.model.location.Location;

public class ConverterToSearchItem {

    public ArrayList<SearchItem> characterToSearchItem(ArrayList<Character> characters) {
        ArrayList<SearchItem> searchItems = new ArrayList<>();
        for(Character character: characters) {
            searchItems.add(new SearchItem(
                    "character",
                    character.id,
                    character.name,
                    character.image
            ));
        }
        return searchItems;
    }

    public ArrayList<SearchItem> locationToSearchItem(ArrayList<Location> locations) {
        ArrayList<SearchItem> searchItems = new ArrayList<>();
        for(Location location: locations) {
            searchItems.add(new SearchItem(
                    "location",
                    location.id,
                    location.name,
                    "null"
            ));
        }
        return searchItems;
    }

    public ArrayList<SearchItem> episodeToSearchItem(ArrayList<Episode> episodes) {
        ArrayList<SearchItem> searchItems = new ArrayList<>();
        for(Episode episode: episodes) {
            searchItems.add(new SearchItem(
                    "episode",
                    episode.id,
                    episode.name,
                    "null"
            ));
        }
        return searchItems;
    }
}
