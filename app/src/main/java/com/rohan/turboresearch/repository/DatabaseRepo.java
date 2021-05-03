package com.rohan.turboresearch.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rohan.turboresearch.room.dao.CarDao;
import com.rohan.turboresearch.room.entity.Cars;
import com.rohan.turboresearch.utils.Constants;

import java.util.List;

import javax.inject.Inject;

public class DatabaseRepo {

    private final CarDao dao;
    LiveData<List<Cars>> cars;

    @Inject
    DatabaseRepo(CarDao carDao) {
        this.dao = carDao;
        cars = dao.getAllCars();
    }

    public void insertData(Cars car) {

        new insertAsyncTask(dao).execute(car);
    }

    public LiveData<List<Cars>> getAllCars() {
        return cars;
    }

    private static class insertAsyncTask extends AsyncTask<Cars, Void, Void> {
        CarDao dao;
        insertAsyncTask(CarDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Cars... cars) {
            Log.d(Constants.TAG,"inserting");
            dao.insert(cars[0]);
            return null;
        }
    }


}




