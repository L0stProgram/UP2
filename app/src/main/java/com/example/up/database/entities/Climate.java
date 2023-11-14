package com.example.up.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Climate")
public class Climate {
    @PrimaryKey
    public long climate_id;
    @ColumnInfo
    public String climate_name;
    @ColumnInfo
    public int humidity;
    @ColumnInfo
    public int temperature;
    public Climate(){}
}
