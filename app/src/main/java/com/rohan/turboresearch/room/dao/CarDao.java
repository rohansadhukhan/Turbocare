package com.rohan.turboresearch.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rohan.turboresearch.room.entity.Cars;

import java.util.List;

@Dao
public interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cars car);

    @Delete
    void delete(Cars car);

    @Query("DELETE FROM vehicles")
    void deleteAll();

    @Query("SELECT * from vehicles")
    LiveData<List<Cars>> getAllCars();

}
