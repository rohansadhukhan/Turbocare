package com.rohan.turboresearch.di;

import android.content.Context;

import androidx.room.Room;

import com.rohan.turboresearch.room.VehicleDatabase;
import com.rohan.turboresearch.room.dao.CarDao;
import com.rohan.turboresearch.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DBModule {

    @Provides
    @Singleton
    VehicleDatabase provideVehicleDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                VehicleDatabase.class,
                Constants.DATABASE_NAME
        ).build();
    }

    @Provides
    @Singleton
    CarDao provideCarDao(VehicleDatabase db) {
        return db.carDao();
    }
}
