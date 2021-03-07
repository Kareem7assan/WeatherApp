package com.kareem.weatherapp.data.repository

import com.kareem.weatherapp.data.cache.WeatherDao
import com.kareem.weatherapp.data.model.WeatherModel
import com.kareem.weatherapp.data.remote.WeatherApi
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val api: WeatherApi,
    private val db: WeatherDao
):WeatherRepository{
    override fun searchWeatherCityCache(city: String): Observable<WeatherModel> {
        return Observable.fromCallable{db.getWeatherCity(city)}
    }

    override fun searchWeatherCityRemote(city: String): Observable<Response<WeatherModel>> {
        return api.getWeatherCity(city = city)
    }

    override fun saveWeather(weather:WeatherModel): Completable {
        return Completable.fromCallable { db.addWeather(weather) }
    }

}