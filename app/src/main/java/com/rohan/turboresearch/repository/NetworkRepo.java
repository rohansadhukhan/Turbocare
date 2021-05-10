package com.rohan.turboresearch.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.rohan.turboresearch.api.APIService;
import com.rohan.turboresearch.room.entity.TestImgur;
import com.rohan.turboresearch.utils.Constants;
import com.rohan.turboresearch.utils.Time;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class NetworkRepo {

    private final APIService api;
    public MutableLiveData<String> vehicleNumber = new MutableLiveData<>();
    public String duration;
    private long startTime, endTime;

    @Inject
    NetworkRepo(APIService api) {
        this.api = api;
    }

    public MutableLiveData<String> uploadData(RequestBody data, MultipartBody.Part part) {
        Observable<String> response = api.uploadData(part);
        response.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        startTime = System.currentTimeMillis();
                        Log.d(Constants.TAG, "Start Time " + startTime);
                    }

                    @Override
                    public void onNext(@NonNull String number) {
                        Log.d(Constants.TAG, "onNext - Vehicle number: " + number);
                        vehicleNumber.postValue(number);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(Constants.TAG, "onError - Error: " + e.getMessage());
                        vehicleNumber.postValue("Error");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(Constants.TAG, "onComplete - Done");
                        endTime = System.currentTimeMillis();
                        Log.d(Constants.TAG, "End time " + endTime);
                        duration = Time.convertMillis(endTime - startTime);
                    }
                });
        return vehicleNumber;
    }

    public MutableLiveData<String> uploadTestData(RequestBody data, MultipartBody.Part part) {
        Observable<TestImgur> response = api.uploadTestData(part);
        response.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<TestImgur>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        startTime = System.currentTimeMillis();
                        Log.d(Constants.TAG, "Start Time " + startTime);
                    }

                    @Override
                    public void onNext(@NonNull TestImgur number) {
                        Log.d(Constants.TAG, "onNext - Vehicle number: " + number.getStatus());
                        vehicleNumber.postValue(number.getStatus());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(Constants.TAG, "onError - Error: " + e.getMessage());
                        vehicleNumber.postValue("Error");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(Constants.TAG, "onComplete - Done");
                        endTime = System.currentTimeMillis();
                        Log.d(Constants.TAG, "End time " + endTime);
                        duration = Time.convertMillis(endTime - startTime);
                    }
                });
        return vehicleNumber;
    }
}
