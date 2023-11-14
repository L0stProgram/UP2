package com.example.up.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.up.database.dao.ClimateDao;
import com.example.up.database.entities.Climate;

@androidx.room.Database(entities = {Climate.class}, version = 2)
public abstract class Database extends RoomDatabase {
    public abstract ClimateDao ClimateDao();

    public static volatile Database INSTANCE;
    public static Database getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (Database.class){
                if (INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            Database.class,
                            "test_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
