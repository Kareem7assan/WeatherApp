package com.kareem.weatherapp

import com.kareem.weatherapp.data.model.City
import com.kareem.weatherapp.data.model.WeatherModel
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody

fun getFakeWeather(): WeatherModel = WeatherModel(id = 1,city = City(name = "mans"))

fun errorResponseBody(): ResponseBody {
    return "{\"key\":[\"somestuff\"]}"
        .toResponseBody("application/json".toMediaTypeOrNull())
}