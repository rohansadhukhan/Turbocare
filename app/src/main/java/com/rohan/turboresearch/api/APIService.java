package com.rohan.turboresearch.api;

import com.rohan.turboresearch.room.entity.TestImgur;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.Response;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {

    @Multipart
    @POST("/")
    Observable<String> uploadData(
            @Part MultipartBody.Part file
    );

    @Multipart
    @POST("3/upload")
    Observable<TestImgur> uploadTestData(
            @Part MultipartBody.Part file
    );

//    @Multipart
//    @POST("/")
//    Observable<String> uploadData(
//            @Part("file") RequestBody file
//    );

}
