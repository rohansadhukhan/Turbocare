package com.rohan.turboresearch.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {


    @Multipart
    @POST("/")
    Call<String> uploadData(
            @Part MultipartBody.Part file
    );

//    @Multipart
//    @POST("/")
//    Call<String> uploadData(
//            @Part("file") RequestBody file
//    );

}
