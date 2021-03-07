package com.kareem.weatherapp.data.model

import androidx.room.*

@Entity(tableName = "weather")
data class WeatherModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @Embedded
    var city: City? = null,
    @ColumnInfo(name = "cnt")
    var cnt: Int? = null,
    @ColumnInfo(name = "code")
    var cod: String? = null,
    @TypeConverters(WeatherListTypeConverters::class)
    @ColumnInfo(name="listWeather")
    var list: MutableList<WeatherList>? = null,
    @ColumnInfo(name = "msg")
    var message: Double? = null

)