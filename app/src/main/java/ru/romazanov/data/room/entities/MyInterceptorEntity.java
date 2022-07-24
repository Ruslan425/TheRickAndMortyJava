package ru.romazanov.data.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_interceptor")
public class MyInterceptorEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String request;
    public String response;

    public MyInterceptorEntity(
            String request,
            String response
    ) {
        this.request = request;
        this.response = response;
    }

}
