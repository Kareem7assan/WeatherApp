package com.kareem.weatherapp.presentation.view.ui
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import com.kareem.weatherapp.R
import com.kareem.weatherapp.data.model.WeatherModel
import com.kareem.weatherapp.data.remote.Cache
import com.kareem.weatherapp.data.remote.Fail
import com.kareem.weatherapp.data.remote.Loading
import com.kareem.weatherapp.data.remote.Success
import com.kareem.weatherapp.databinding.MainFragmentBinding
import com.kareem.weatherapp.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jetbrains.anko.support.v4.toast
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private val mAdapter by lazy { WeatherAdapter() }
    private  var binding: MainFragmentBinding?=null
    private val weatherViewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(binding!!){
            rvForcasts.apply {
                layoutManager=LinearLayoutManager(requireContext())
                adapter=mAdapter
            }

            etSearch.textChanges()
                    .skipInitialValue()
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it.isNotBlank()) {
                            retryBtn.enable()
                            weatherViewModel.searchCity(it.toString())
                        }
                        else{
                            retryBtn.disable()
                        }
                    }


        }
        weatherViewModel.viewState.observe(viewLifecycleOwner, {state->
            when(state){
                is Loading ->{showProgress(state.isVisible)}
                is Success ->{showSuccess(state.data as WeatherModel).also { hideWarning() }}
                is Cache ->{showSuccess(state.data as WeatherModel).also { showWarning() }}
                is Fail ->{showError(state.error as Throwable).also { hideWarning() }}
            }
        })

        setActions()
    }

    private fun hideWarning() {
        binding?.tvWarning?.visibility=View.GONE
    }

    private fun showWarning() {
        binding?.tvWarning?.visibility=View.VISIBLE
    }

    private fun setActions() {
        with(binding!!){
            retryBtn.setOnClickListener {
                weatherViewModel.searchCity(etSearch.text.toString())
            }
        }
    }

    private fun showSuccess(weather: WeatherModel) {
        with(binding!!) {
            errorLay.visibility = View.GONE
            rvForcasts.visibility = View.VISIBLE
        }
        weather.list?.let { mAdapter.swapData(it) }
    }

    private fun showError(error: Throwable) {
         //error.message?.let { Log.e("error", it) }
        with(binding!!) {
            errorLay.visibility = View.VISIBLE
            rvForcasts.visibility = View.GONE
            errorMsg.text=error.message
        }
    }



    private fun showProgress(visible: Boolean) {
        with(binding!!) {
            if (visible) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        }
    }
    fun View.enable(){
        alpha=1f
        isEnabled=true
    }
    fun View.disable(){
        alpha=0.5f
        isEnabled=false
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}