package com.kareem.weatherapp.domain.exception

class CustomDataExeception(private val msg:String?="city not found"): Throwable() {
    override val message: String?
        get() = msg
}