package com.rohan.turboresearch.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.rohan.turboresearch.api.APIService;
import com.rohan.turboresearch.utils.Constants;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepo {

    private final APIService api;
    public MutableLiveData<String> vehicleNumber = new MutableLiveData<>();

    @Inject
    NetworkRepo(APIService api) {
        this.api = api;
    }

    public MutableLiveData<String> uploadData(RequestBody data, MultipartBody.Part body) {
        Observable<String> response = api.uploadData(data);
        response.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String number) {
                        Log.d(Constants.TAG, "onNext - Vehicle number: " + number);
                        vehicleNumber.postValue(number);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(Constants.TAG, "onError - Error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(Constants.TAG, "onComplete - Done");
                    }
                });
        return vehicleNumber;
    }
}
