package com.example.up.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.up.database.entities.Climate;

import java.util.List;

@Dao
public interface ClimateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Climate climate);
    @Update
    void update(Climate climate);
    @Delete
    void delete(Climate climate);

    @Query("SELECT * FROM Climate")
    List<Climate> getAllClimate();
}
