package com.kareem.weatherapp.presentation.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kareem.weatherapp.data.model.WeatherModel
import com.kareem.weatherapp.data.remote.Fail
import com.kareem.weatherapp.data.remote.Loading
import com.kareem.weatherapp.data.remote.Success
import com.kareem.weatherapp.data.remote.ViewState
import com.kareem.weatherapp.domain.exception.CustomDataExeception
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.IllegalStateException
import java.net.ConnectException

open class BaseViewModel:ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    open val viewState = MutableLiveData<ViewState<Any>>()
    protected fun addDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    private fun dispose() = compositeDisposable.dispose()


    fun <T> Observable<Response<T>>.subscribeWithLoading(
        onSuccess: (T) -> Unit,
        onDataError: (throwable: Throwable) -> Unit  ,
        onGeneralError: (throwable: Throwable) -> Unit ) {
        doOnSubscribe { viewState.value= Loading(true) }
        .doFinally{ viewState.value= Loading(false) }
        .subscribe ({
            if ((it.isSuccessful)) onSuccess(it.body()!!) else throw CustomDataExeception()
        },{
            if (it is CustomDataExeception){
                onDataError(it)
            }
            else{
                onGeneralError(it)
            }
        })
        .also { addDisposable(it) }


    }




    override fun onCleared() {
        dispose()
        super.onCleared()
    }
}