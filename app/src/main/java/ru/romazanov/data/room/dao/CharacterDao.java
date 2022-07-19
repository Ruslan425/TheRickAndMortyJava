package ru.romazanov.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import ru.romazanov.data.model.character.Character;
import ru.romazanov.data.room.entities.CharacterEntity;


@Dao
public interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCharacterList(List<CharacterEntity> characters);

    @Update
    void updateCharacterList(ArrayList<CharacterEntity> characters);

    @Insert
    void addCharacter(CharacterEntity character);

    @Update
    void updateCharacter(CharacterEntity character);

    @Query("SELECT * FROM character")
    List<CharacterEntity> getAll();

}
