package com.kareem.weatherapp.data.remote


sealed class ViewState<out T : Any>
class Success<out T : Any>(val data: T) : ViewState<T>()
class Fail<out T : Any>(val error: T, val errorCode: Int? = -1) : ViewState<T>()
class Loading<out T : Any>(val isVisible: Boolean) : ViewState<T>()
class Cache<out T : Any>(val data: T) : ViewState<T>()
