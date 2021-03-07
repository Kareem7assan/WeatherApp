package com.kareem.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.TypeConverters

data class WeatherList(
    @TypeConverters(WeatherTypeConverter::class)
    @ColumnInfo(name = "weatherListInfo")
    val weather: MutableList<Weather>,
    @ColumnInfo(name = "weatherClouds")
    var clouds: Int? = null,
    @ColumnInfo(name = "weatherDeg")
    var deg: Int? = null,
    @ColumnInfo(name = "weatherDT")
    var dt: Int? = null,
    @ColumnInfo(name = "weatherFeelLike")
    var feels_like: Temp? = null,
    @ColumnInfo(name = "weatherHumidity")
    var humidity: Int? = null,
    @ColumnInfo(name = "weatherPop")
    var pop: Int? = null,
    @ColumnInfo(name = "weatherPressure")
    var pressure: Int? = null,
    @ColumnInfo(name = "weatherSpeed")
    var speed: Double? = null,
    @ColumnInfo(name = "weatherSunRise")
    var sunrise: Int? = null,
    @ColumnInfo(name = "weatherSunSet")
    var sunset: Int? = null,
    @ColumnInfo(name = "weatherTemp")
    var temp: Temp? = null
)