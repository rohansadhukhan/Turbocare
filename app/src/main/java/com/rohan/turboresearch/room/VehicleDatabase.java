package com.rohan.turboresearch.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.rohan.turboresearch.room.dao.CarDao;
import com.rohan.turboresearch.room.entity.Cars;

@Database(entities = {Cars.class}, version = 1, exportSchema = false)
public abstract class VehicleDatabase extends RoomDatabase {
    public abstract CarDao carDao();
}