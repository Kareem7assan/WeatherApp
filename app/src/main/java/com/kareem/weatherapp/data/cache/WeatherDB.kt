package com.kareem.weatherapp.data.cache
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kareem.weatherapp.data.model.Weather
import com.kareem.weatherapp.data.model.WeatherListTypeConverters
import com.kareem.weatherapp.data.model.WeatherModel
import com.kareem.weatherapp.data.model.WeatherTypeConverter


@Database(entities = [WeatherModel::class], version = 1, exportSchema = false)
@TypeConverters(WeatherTypeConverter::class,WeatherListTypeConverters::class)
abstract class WeatherDB : RoomDatabase(){
    abstract fun weatherDao(): WeatherDao

}
