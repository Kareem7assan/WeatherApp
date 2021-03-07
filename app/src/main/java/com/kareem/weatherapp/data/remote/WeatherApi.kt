package com.kareem.weatherapp.data.remote

import com.kareem.weatherapp.data.model.WeatherModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("daily")
    fun getWeatherCity(
        @Query("q") city:String,
        @Query("cnt") cnt:String="5",
        @Query("units") unit:String="metric",
        @Query("appid") apiKey:String="9228c5e4462e26921161c0294ca76594"
    ): Observable<Response<WeatherModel>>

}