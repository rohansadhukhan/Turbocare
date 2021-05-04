package com.rohan.turboresearch.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rohan.turboresearch.api.APIService;
import com.rohan.turboresearch.utils.Constants;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    public static String provideBaseURL() {
        return Constants.BASE_URL;
    }

    @Provides
    public static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.MINUTES)
                .connectTimeout(10, TimeUnit.MINUTES)
                .build();
    }

    @Provides
    public static Retrofit provideRetrofit(
            String baseURL,
            OkHttpClient client
    ) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @Provides
    public static APIService provideAPI(
            Retrofit retrofit
    ) {
        return retrofit.create(APIService.class);
    }

}
