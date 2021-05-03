package com.rohan.turboresearch.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rohan.turboresearch.repository.DatabaseRepo;
import com.rohan.turboresearch.repository.NetworkRepo;
import com.rohan.turboresearch.room.entity.Cars;
import com.rohan.turboresearch.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.scopes.ViewModelScoped;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

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

    public void uploadData(RequestBody data, MultipartBody.Part body) {
        vehicleNumber = networkRepo.uploadData(data, body);
    }

    public void insertData(String path) {
        databaseRepo.insertData(new Cars(path, "MH 12 AB 1234", "185", "12/05/21"));
    }

    public LiveData<List<Cars>> getAllCars() {
        Log.d(Constants.TAG, "cars size " + cars.getValue().size());
        return cars;
    }

}
