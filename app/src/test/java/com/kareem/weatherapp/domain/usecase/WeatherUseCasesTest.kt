package com.kareem.weatherapp.domain.usecase

import com.kareem.weatherapp.RxSchedulerRule
import com.kareem.weatherapp.data.model.WeatherModel
import com.kareem.weatherapp.data.repository.WeatherRepository
import com.kareem.weatherapp.getFakeWeather
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Response


class WeatherUseCasesTest {
    private lateinit var weatherUseCases: WeatherUseCases

    @Mock
    lateinit var repository: WeatherRepository

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        weatherUseCases = WeatherUseCases(repository)


    }

    @Test
    fun `searchCityUseCase() with right city parm assert complete`() {
        val existingCity = Mockito.anyString()
        val fakeWeather = getFakeWeather()
        Mockito.`when`(repository.searchWeatherCityRemote(existingCity)).thenReturn(
            Observable.just(
                Response.success(
                    fakeWeather
                )
            )
        )
        val test = repository.searchWeatherCityRemote("cairo").test()
        test.assertComplete()
    }
    @Test
    fun `searchCityUseCase() with right city parm then invoke saveWeather()`() {
        val existingCity = Mockito.anyString()
        val fakeWeather = getFakeWeather()
        Mockito.`when`(repository.searchWeatherCityRemote(existingCity)).thenReturn(
            Observable.just(
                Response.success(
                    fakeWeather
                )
            )
        )
        val testObserver =
            TestObserver<Response<WeatherModel>>()

        weatherUseCases.searchCityUseCase(existingCity).subscribe(testObserver)
        verify(repository, times(1)).saveWeather(fakeWeather)
    }

    @Test
    fun `searchWeatherCityCacheUseCase() with right city parm assert complete`() {
        val existingCity = Mockito.anyString()
        Mockito.`when`(repository.searchWeatherCityCache(existingCity)).thenReturn(
            Observable.just(
                getFakeWeather()
            )
        )
        val test = repository.searchWeatherCityCache("cairo").test()
        test.assertComplete()
    }

    @Test
    fun `searchCityUseCase() with wrong city parm assert not complete`() {
        val notExistingCity = Mockito.anyString()
        Mockito.`when`(repository.searchWeatherCityRemote(notExistingCity)).thenReturn(
            Observable.error(
                Throwable()
            )
        )
        val test = repository.searchWeatherCityRemote("cairoo").test()
        test.assertNotComplete()
    }
    @Test
    fun `searchWeatherCityCacheUseCase() with wrong city parm assert not complete`() {
        val notExistingCity = Mockito.anyString()
        Mockito.`when`(repository.searchWeatherCityCache(notExistingCity)).thenReturn(
            Observable.error(
                Throwable()
            )
        )
        val test = repository.searchWeatherCityCache("cairoo").test()
        test.assertNotComplete()
    }

    @Test
    fun `searchCityUseCase() with wrong city parm not then invoke saveWeather()`() {
        val notExistingCity = Mockito.anyString()
        val fakeWeather = getFakeWeather()
        Mockito.`when`(repository.searchWeatherCityRemote(notExistingCity)).thenReturn(
            Observable.error(
                Throwable()
            )
        )
        val testObserver = TestObserver<Response<WeatherModel>>()
        weatherUseCases.searchCityUseCase(notExistingCity).subscribe(testObserver)
        verify(repository, times(0)).saveWeather(fakeWeather)
    }


}