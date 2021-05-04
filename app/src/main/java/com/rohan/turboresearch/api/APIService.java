package com.rohan.turboresearch.api;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {


//    @Multipart
//    @POST("/")
//    Call<String> uploadData(
//            @Part MultipartBody.Part file
//    );

    @Multipart
    @POST("/")
    Observable<String> uploadData(
            @Part("file") RequestBody file
    );

}
