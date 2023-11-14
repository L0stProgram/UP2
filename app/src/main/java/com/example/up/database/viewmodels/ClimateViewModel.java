package com.example.up.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.up.database.Database;
import com.example.up.database.entities.Climate;

import java.util.List;

public class ClimateViewModel extends AndroidViewModel {
    private Database database;
    public List<Climate> climates;
    public ClimateViewModel(@NonNull Application application){
        super(application);
        database = Database.getDatabase(getApplication());
    }

    public void addClimate(Climate item){
        Runnable addClimateRnb = ()->{
            database.ClimateDao().insert(item);
        };
        Thread thread = new Thread(addClimateRnb);
        thread.start();
    }

    public void deleteClimate(Climate item){
        Runnable deleteClimateRnb = ()->{
            database.ClimateDao().delete(item);
        };
        Thread thread = new Thread(deleteClimateRnb);
        thread.start();
    }

    public void updateClimate(Climate item){
        Runnable updateClimateRnb = ()->{
            database.ClimateDao().update(item);
        };
        Thread thread = new Thread(updateClimateRnb);
        thread.start();
    }
}
