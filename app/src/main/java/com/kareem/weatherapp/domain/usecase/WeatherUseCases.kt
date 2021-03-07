package com.kareem.weatherapp.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.kareem.weatherapp.data.model.WeatherModel
import com.kareem.weatherapp.data.remote.ViewState
import com.kareem.weatherapp.data.repository.WeatherRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class WeatherUseCases @Inject constructor(private val repository: WeatherRepository) {

    fun searchCityUseCase(city: String):  Observable<Response<WeatherModel>> {
                return repository.searchWeatherCityRemote(city)
                    .doOnNext {data-> repository.saveWeather(data?.body()!!).subscribe() }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchWeatherCityCacheUseCase(city: String):  Observable<WeatherModel>{
        return repository.searchWeatherCityCache(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}