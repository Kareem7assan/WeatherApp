package com.kareem.weatherapp.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import com.kareem.weatherapp.data.remote.Cache
import com.kareem.weatherapp.data.remote.Fail
import com.kareem.weatherapp.data.remote.Loading
import com.kareem.weatherapp.data.remote.Success
import com.kareem.weatherapp.domain.exception.CustomDataExeception
import com.kareem.weatherapp.domain.usecase.WeatherUseCases
import com.kareem.weatherapp.domain.utils.NetWorkUtils
import com.kareem.weatherapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.HttpException
import java.net.ConnectException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val useCases: WeatherUseCases) :BaseViewModel(){

    fun searchCity(cityName: String){
            useCases.searchCityUseCase(cityName)
                    .subscribeWithLoading(onSuccess = { viewState.value = Success(it) }, onGeneralError = { invokeUseCaseCache(cityName) }, onDataError = { viewState.value = Fail(it) })


    }

    private fun invokeUseCaseCache(cityName: String) {
        useCases.searchWeatherCityCacheUseCase(cityName)
                .doOnSubscribe { viewState.value= Loading(true) }
                .doFinally{ viewState.value= Loading(false) }
                .subscribe({viewState.value=Cache(it)},{viewState.value=Fail(CustomDataExeception("data not found, check your connection"))})
                .also { addDisposable(it) }
    }



}