package com.rohan.turboresearch.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.rohan.turboresearch.api.APIService;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepo {

    private final APIService api;
    MutableLiveData<String> vehicleNumber = new MutableLiveData<>();

    @Inject
    NetworkRepo(APIService api) {
        this.api = api;
    }

    public MutableLiveData<String> uploadData(RequestBody data, MultipartBody.Part body) {
//        Call<String> call = api.uploadData(data);
        Call<String> call = api.uploadData(body);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                if (response.isSuccessful()){
                    vehicleNumber.setValue(response.body());
                    Log.d("rohan", "vehicle number is: " + vehicleNumber);
                }
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                Log.d("Error", "At repo message: " + t.getMessage());
            }
        });
        Log.d("rohan", "vehicle number is: " + vehicleNumber);
        return vehicleNumber;
    }
}
