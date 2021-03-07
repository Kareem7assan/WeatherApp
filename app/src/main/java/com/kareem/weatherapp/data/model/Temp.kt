package com.kareem.weatherapp.data.model

import androidx.room.ColumnInfo

data class Temp(
    @ColumnInfo(name = "tempDay")
    var day: Double? = null,
    @ColumnInfo(name = "tempeEve")
    var eve: Double? = null,
    @ColumnInfo(name = "tempMax")
    var max: Double? = null,
    @ColumnInfo(name = "tempMin")
    var min: Double? = null,
    @ColumnInfo(name = "tempMorn")
    var morn: Double? = null,
    @ColumnInfo(name = "tempNight")
    var night: Double? = null
)