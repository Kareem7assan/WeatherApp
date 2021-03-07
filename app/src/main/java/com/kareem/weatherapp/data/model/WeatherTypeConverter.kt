package com.kareem.weatherapp.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class WeatherTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun stringToWeather(data: String?): List<Weather?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Weather?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun weatherToString(weather: List<Weather?>?): String? {
        return gson.toJson(weather)
    }
}