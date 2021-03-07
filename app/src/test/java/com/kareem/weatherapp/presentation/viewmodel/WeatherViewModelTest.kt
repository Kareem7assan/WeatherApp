package com.kareem.weatherapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kareem.weatherapp.RxSchedulerRule
import com.kareem.weatherapp.data.model.WeatherModel
import com.kareem.weatherapp.data.remote.*
import com.kareem.weatherapp.data.repository.WeatherRepository
import com.kareem.weatherapp.domain.exception.CustomDataExeception
import com.kareem.weatherapp.domain.usecase.WeatherUseCases
import com.kareem.weatherapp.getFakeWeather
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import retrofit2.Response
import java.io.IOException
import java.lang.UnsupportedOperationException
import java.net.ConnectException
import kotlin.jvm.Throws

class WeatherViewModelTest {

     lateinit var weatherViewModel: WeatherViewModel
    @Mock
    lateinit var weatherUseCases: WeatherUseCases


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()
    @Mock
    private lateinit var observerState: Observer<ViewState<Any>>

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<ViewState<Any>>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        weatherViewModel = WeatherViewModel(weatherUseCases)
    }
    //TODO("searchCity("happy Scenario")")
    @Test
    fun `searchCity() with right city  then invoked state 3 times`() {
        val existingCity = Mockito.anyString()
        val fakeWeather = getFakeWeather()
        Mockito.`when`(weatherUseCases.searchCityUseCase(existingCity)).thenReturn(Observable.just(Response.success(fakeWeather)))
        weatherViewModel.viewState.observeForever(observerState)
        weatherViewModel.searchCity("cairo")
        Mockito.verify(observerState, Mockito.times(3)).onChanged(argumentCaptor.capture())

    }
    @Test
    fun `searchCity() with right city  then then loading(true) invoked`() {
        val existingCity = Mockito.anyString()
        val fakeWeather = getFakeWeather()
        Mockito.`when`(weatherUseCases.searchCityUseCase(existingCity)).thenReturn(Observable.just(Response.success(fakeWeather)))
        weatherViewModel.viewState.observeForever(observerState)
        weatherViewModel.searchCity("cairo")
        Mockito.verify(observerState, Mockito.times(3)).onChanged(argumentCaptor.capture())
        val loading = argumentCaptor.allValues[0] as Loading
        assertTrue(loading.isVisible)
    }
    @Test
    fun `searchCity() with right city  then then Success invoked`() {
        val existingCity = Mockito.anyString()
        val fakeWeather = getFakeWeather()
        Mockito.`when`(weatherUseCases.searchCityUseCase(existingCity)).thenReturn(Observable.just(Response.success(fakeWeather)))
        weatherViewModel.viewState.observeForever(observerState)
        weatherViewModel.searchCity("cairo")
        Mockito.verify(observerState, Mockito.times(3)).onChanged(argumentCaptor.capture())
        val successCase = argumentCaptor.allValues[1] as Success
        assertEquals(successCase.data as WeatherModel ,fakeWeather)
    }
    @Test
    fun `searchCity() with right city  then then loading(false) invoked`() {
        val existingCity = Mockito.anyString()
        val fakeWeather = getFakeWeather()
        Mockito.`when`(weatherUseCases.searchCityUseCase(existingCity)).thenReturn(Observable.just(Response.success(fakeWeather)))
        weatherViewModel.viewState.observeForever(observerState)
        weatherViewModel.searchCity("cairo")
        Mockito.verify(observerState, Mockito.times(3)).onChanged(argumentCaptor.capture())
        val loading = argumentCaptor.allValues[2] as Loading
        assertTrue(loading.isVisible.not())
    }
    //TODO("searchCity("bad Scenari0")")
    //general error  notExistingCity as example
    @Test
    fun `searchCity() with general error then invoked state 3 times`() {
        val notExistingCity = Mockito.anyString()
        Mockito.`when`(weatherUseCases.searchCityUseCase(notExistingCity)).thenReturn(Observable.error(CustomDataExeception()))
        weatherViewModel.viewState.observeForever(observerState)
        weatherViewModel.searchCity("cair0")
        Mockito.verify(observerState, Mockito.times(3)).onChanged(argumentCaptor.capture())

    }

    @Test
    fun `searchCity() with general error then loading(true) invoked`() {
        val notExistingCity = Mockito.anyString()
        val fakeWeather = getFakeWeather()
        Mockito.`when`(weatherUseCases.searchCityUseCase(notExistingCity)).thenReturn(Observable.error(CustomDataExeception()))
        weatherViewModel.viewState.observeForever(observerState)
        weatherViewModel.searchCity("cair0")
        Mockito.verify(observerState, Mockito.times(3)).onChanged(argumentCaptor.capture())
        val loading = argumentCaptor.allValues[0] as Loading
        assertTrue(loading.isVisible)
    }
   @Test
    fun `searchCity() with general error then Fail`() {
        val notExistingCity = Mockito.anyString()
        val fakeWeather = getFakeWeather()
        Mockito.`when`(weatherUseCases.searchCityUseCase(notExistingCity)).thenReturn(Observable.error(CustomDataExeception()))
        weatherViewModel.viewState.observeForever(observerState)
        weatherViewModel.searchCity("cairo0")
        Mockito.verify(observerState, Mockito.times(3)).onChanged(argumentCaptor.capture())
        assertTrue(argumentCaptor.allValues[1] is Fail)
    }
    @Test
    fun `searchCity() with general error then loading(false) invoked`() {
        val notExistingCity = Mockito.anyString()
        val fakeWeather = getFakeWeather()
        Mockito.`when`(weatherUseCases.searchCityUseCase(notExistingCity)).thenReturn(Observable.error(CustomDataExeception()))
        weatherViewModel.viewState.observeForever(observerState)
        weatherViewModel.searchCity("cairo0")
        Mockito.verify(observerState, Mockito.times(3)).onChanged(argumentCaptor.capture())
        val loading = argumentCaptor.allValues[2] as Loading
        assertTrue(loading.isVisible.not())
    }
    //3rd scenario
    //error related with connection
    @Test
    fun `searchCity() with connection error then invoked state 2 times`() {
        val existingCity = Mockito.anyString()
        Mockito.`when`(weatherUseCases.searchCityUseCase(existingCity)).thenReturn(Observable.error(ConnectException()))
        weatherViewModel.viewState.observeForever(observerState)
        weatherViewModel.searchCity("cairo")
        Mockito.verify(observerState, Mockito.times(2)).onChanged(argumentCaptor.capture())

    }

    @Test
    fun `searchCity() with connection error then invoked invokeUseCaseCache()`() {
        val notExistingCity = Mockito.anyString()
        val fakeWeather = getFakeWeather()
        Mockito.`when`(weatherUseCases.searchCityUseCase(notExistingCity)).thenReturn(Observable.error(ConnectException()))
        weatherViewModel.viewState.observeForever(observerState)
        weatherViewModel.searchCity("cairo")
        Mockito.verify(weatherUseCases, Mockito.times(1)).searchWeatherCityCacheUseCase("cairo")

    }


}