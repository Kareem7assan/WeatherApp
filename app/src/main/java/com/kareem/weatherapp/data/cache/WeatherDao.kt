package com.kareem.weatherapp.data.cache
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kareem.weatherapp.data.model.WeatherModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single


@Dao
interface WeatherDao {
    @Query("select * from weather where cityName like :city order by id desc limit 1")
    fun getWeatherCity(city:String): WeatherModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWeather(weather:WeatherModel)

}
