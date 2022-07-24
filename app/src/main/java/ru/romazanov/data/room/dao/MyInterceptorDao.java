package ru.romazanov.data.room.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.romazanov.data.room.entities.MyInterceptorEntity;

@Dao
public interface MyInterceptorDao {

    @Insert
    void addLogg(MyInterceptorEntity myInterceptorEntity);

    @Query("SELECT * FROM my_interceptor")
    LiveData<List<MyInterceptorEntity>> getAll();

}
