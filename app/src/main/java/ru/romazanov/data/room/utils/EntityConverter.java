package ru.romazanov.data.room.utils;

import java.util.ArrayList;
import java.util.List;

import ru.romazanov.data.model.character.Character;
import ru.romazanov.data.model.episode.Episode;
import ru.romazanov.data.model.location.Location;
import ru.romazanov.data.room.entities.CharacterEntity;
import ru.romazanov.data.room.entities.EpisodeEntity;
import ru.romazanov.data.room.entities.LocationEntity;

public class EntityConverter {

    public ArrayList<Location> getLocationFromEntity(List<LocationEntity> list) {
        ArrayList<Location> list1 = new ArrayList<>();
        for (LocationEntity locationEntity : list) {
            list1.add( new Location(
                    locationEntity.id,
                    locationEntity.name,
                    locationEntity.type,
                    locationEntity.dimension,
                    locationEntity.residents,
                    locationEntity.url,
                    locationEntity.created
            ));
        }
        return list1;
    }

    public ArrayList<LocationEntity> getEntityFromLocation(ArrayList<Location> list) {
        ArrayList<LocationEntity> list1 = new ArrayList<>();
        for (Location location : list) {
            list1.add(new LocationEntity(
                    location.id,
                    location.name,
                    location.type,
                    location.dimension,
                    location.residents,
                    location.url,
                    location.created
            ));
        }
        return list1;
    }

    public ArrayList<Character> getCharacterFromEntity(List<CharacterEntity> list) {
        ArrayList<Character> list1 = new ArrayList<>();
        for (CharacterEntity characterEntity : list) {
            list1.add(new Character(
                    characterEntity.id,
                    characterEntity.name,
                    characterEntity.status,
                    characterEntity.species,
                    characterEntity.type,
                    characterEntity.gender,
                    characterEntity.origin,
                    characterEntity.location,
                    characterEntity.image,
                    characterEntity.episode,
                    characterEntity.url,
                    characterEntity.created));
        }
        return list1;
    }

    public ArrayList<CharacterEntity> getEntityFromCharacter(ArrayList<Character> list) {
        ArrayList<CharacterEntity> list1 = new ArrayList<>();
        for (Character character : list) {
            list1.add(new CharacterEntity(
                    character.id,
                    character.name,
                    character.status,
                    character.species,
                    character.type,
                    character.gender,
                    character.origin,
                    character.location,
                    character.image,
                    character.episode,
                    character.url,
                    character.created));
        }
        return list1;
    }

    public ArrayList<Episode> getEpisodeFromEntity(List<EpisodeEntity> list) {
        ArrayList<Episode> list1 = new ArrayList<>();
        for (EpisodeEntity episodeEntity : list) {
            list1.add(new Episode(
                    episodeEntity.id,
                    episodeEntity.name,
                    episodeEntity.air_date,
                    episodeEntity.episode,
                    episodeEntity.characters,
                    episodeEntity.url,
                    episodeEntity.created
            ));
        }
        return list1;
    }

    public ArrayList<EpisodeEntity> getEntityFromEpisode(ArrayList<Episode> list) {
        ArrayList<EpisodeEntity> list1 = new ArrayList<>();
        for (Episode episode : list) {
            list1.add(new EpisodeEntity(
                    episode.id,
                    episode.name,
                    episode.air_date,
                    episode.episode,
                    episode.characters,
                    episode.url,
                    episode.created
            ));
        }
        return list1;
    }

}
