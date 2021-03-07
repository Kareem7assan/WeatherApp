package com.kareem.weatherapp.data.model

import androidx.room.ColumnInfo

data class Weather(
    @ColumnInfo(name = "weathersDesc")
    var description: String? = null,
    @ColumnInfo(name = "weathersIcon")
    var icon: String? = null,
    @ColumnInfo(name = "weathersId")
    var id: Int? = null,
    @ColumnInfo(name = "weathersMain")
    var main: String? = null
){
}