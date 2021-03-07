package com.kareem.weatherapp.presentation.view.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kareem.weatherapp.R
import com.kareem.weatherapp.data.model.WeatherList
import com.kareem.weatherapp.databinding.ItemWeatherBinding
import java.util.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherVH>() {

    private var data: MutableList<WeatherList> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherVH {
        return WeatherVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_weather, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: WeatherVH, position: Int) = holder.bind(data[position])

    fun swapData(data: List<WeatherList>) {
        this.data = data as MutableList<WeatherList>
        notifyDataSetChanged()
    }

    fun removeWithIndex(index: Int) {
        data.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, itemCount)
    }

    class WeatherVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item:WeatherList) = with(ItemWeatherBinding.bind(itemView)) {
            weatherDesc.text=item.weather.first().description
            weatherTemp.text="high:${item.temp?.max} "+" low:${item.temp?.min}"
            weatherHumidity.text="humidity:${item.humidity}"
        }
    }
}