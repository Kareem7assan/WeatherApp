package com.kareem.weatherapp.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class WeatherListTypeConverters {
    var gson = Gson()

    @TypeConverter

    fun stringToWeatherList(data: String?): List<WeatherList?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<WeatherList?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun weatherListToString(weatherList: List<WeatherList?>?): String? {
        return gson.toJson(weatherList)
    }
}