package com.kareem.weatherapp.di

import com.kareem.weatherapp.data.repository.WeatherRepository
import com.kareem.weatherapp.domain.usecase.WeatherUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun providesWeatherUseCase(weatherRepository: WeatherRepository): WeatherUseCases {
        return WeatherUseCases(weatherRepository)
    }

}

