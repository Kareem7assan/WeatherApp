package com.kareem.weatherapp.presentation.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kareem.weatherapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherFragment())
                .commitNow()
        }
    }
}