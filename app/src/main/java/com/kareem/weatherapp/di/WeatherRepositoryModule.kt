package com.kareem.weatherapp.di


import com.kareem.weatherapp.data.repository.WeatherRepository
import com.kareem.weatherapp.data.repository.WeatherRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherRepositoryModule {
    @Binds
    abstract fun providesWeatherRepo(weatherRepository: WeatherRepositoryImp): WeatherRepository

}