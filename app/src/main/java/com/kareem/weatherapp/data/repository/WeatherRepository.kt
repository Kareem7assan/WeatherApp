package com.kareem.weatherapp.data.repository

import com.kareem.weatherapp.data.model.WeatherModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface WeatherRepository {
    fun searchWeatherCityCache(city:String): Observable<WeatherModel>
    fun searchWeatherCityRemote(city:String):Observable<Response<WeatherModel>>
    fun saveWeather(weather: WeatherModel):Completable
}