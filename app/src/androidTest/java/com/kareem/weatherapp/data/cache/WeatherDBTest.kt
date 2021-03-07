package com.kareem.weatherapp.data.cache

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kareem.weatherapp.data.model.City
import com.kareem.weatherapp.data.model.WeatherModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WeatherDBTest {
    private lateinit var weatherDao: WeatherDao
    private lateinit var db: WeatherDB

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, WeatherDB::class.java).build()
        weatherDao = db.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    fun addWeatherTest_happy_scenario(){
        val expected = WeatherModel(100, city = City(name = "mans"))
        weatherDao.addWeather(expected)
        val result = weatherDao.getWeatherCity("mans")
        assertThat(expected).isEqualTo(result)
    }

    @Test
    fun addWeatherTest_bad_scenario(){
        val result = weatherDao.getWeatherCity("manso")
        assertThat(result).isEqualTo(null)
    }
}