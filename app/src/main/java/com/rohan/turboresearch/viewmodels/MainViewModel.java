package com.rohan.turboresearch.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rohan.turboresearch.repository.NetworkRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.scopes.ViewModelScoped;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

@HiltViewModel
public class MainViewModel extends ViewModel {

    public MutableLiveData<String> vehicleNumber = new MutableLiveData<>();
    public MutableLiveData<String> testString = new MutableLiveData<>();
    private NetworkRepo networkRepo;

    @Inject
    public MainViewModel(
            NetworkRepo repo
    ) {
        this.networkRepo = repo;
    }

    public void uploadData(RequestBody data, MultipartBody.Part body) {
//        String response = networkRepo.uploadData(data);
        vehicleNumber = networkRepo.uploadData(data, body);
    }

}
