package com.kareem.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Ignore

data class City(

    @ColumnInfo(name="countryName")
    var country: String? = null,
    @ColumnInfo(name="cityId")
    var id: Int? = null,
    @ColumnInfo(name="cityName")
    var name: String? = null,

)