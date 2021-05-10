package com.rohan.turboresearch.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rohan.turboresearch.repository.DatabaseRepo;
import com.rohan.turboresearch.repository.NetworkRepo;
import com.rohan.turboresearch.room.entity.Cars;
import com.rohan.turboresearch.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@HiltViewModel
public class MainViewModel extends ViewModel {

    public MutableLiveData<String> vehicleNumber = new MutableLiveData<>();
    public LiveData<List<Cars>> cars;

    private final NetworkRepo networkRepo;
    private final DatabaseRepo databaseRepo;

    @Inject
    public MainViewModel(
            NetworkRepo networkRepo,
            DatabaseRepo databaseRepo
    ) {
        this.networkRepo = networkRepo;
        this.databaseRepo = databaseRepo;
        cars = databaseRepo.getAllCars();
    }

    public MutableLiveData<String> uploadData(RequestBody body, MultipartBody.Part part) {
        vehicleNumber = networkRepo.uploadTestData(body, part);
        return vehicleNumber;
    }

    public void insertData(String path) {
        String timeStamp = new SimpleDateFormat("HH:mma dd/MM/yyyy ", Locale.getDefault()).format(new Date());
        Cars car = new Cars(path, vehicleNumber.getValue(), networkRepo.duration, timeStamp);
        Log.d(Constants.TAG, car.getDate());
        databaseRepo.insertData(car);
    }

    public LiveData<List<Cars>> getAllCars() {
        return cars;
    }

}
