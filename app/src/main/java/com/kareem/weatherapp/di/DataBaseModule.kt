package com.kareem.weatherapp.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.kareem.weatherapp.data.cache.WeatherDB
import com.kareem.weatherapp.data.cache.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

        @Provides
        fun provideChannelDao(weatherDB: WeatherDB): WeatherDao = weatherDB.weatherDao()

        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext appContext: Context): WeatherDB {
            return Room.databaseBuilder(
                appContext,
                WeatherDB::class.java,
                "weather_DB"
            ).build()
        }
}