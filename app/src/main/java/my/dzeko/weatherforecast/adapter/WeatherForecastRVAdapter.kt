package my.dzeko.weatherforecast.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import my.dzeko.weatherforecast.R
import my.dzeko.weatherforecast.databinding.WeatherForecastCurrentRvItemBinding
import my.dzeko.weatherforecast.databinding.WeatherForecastRvItemBinding
import my.dzeko.weatherforecast.entity.WeatherForecast
import java.lang.IllegalArgumentException

private const val CURRENT_FORECAST = 0
private const val FUTURE_FORECAST = 1

class WeatherForecastRVAdapter(private var mWeatherForecasts :List<WeatherForecast>)
    : RecyclerView.Adapter<WeatherForecastRVAdapter.WeatherForecastVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): WeatherForecastVH {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when(type) {
            CURRENT_FORECAST -> {
               val binding = DataBindingUtil
                    .inflate<WeatherForecastCurrentRvItemBinding>(layoutInflater,
                        R.layout.weather_forecast_current_rv_item,
                        parent,
                        false)
                CurrentWeatherForecastVH(binding)
            }
            FUTURE_FORECAST -> {
                val binding = DataBindingUtil
                    .inflate<WeatherForecastRvItemBinding>(layoutInflater,
                        R.layout.weather_forecast_rv_item,
                        parent,
                        false)
                FutureWeatherForecastVH(binding)
            }

            else -> throw IllegalArgumentException("Wrong view type")
        }
    }

    override fun getItemCount(): Int {
        return mWeatherForecasts.size
    }

    override fun onBindViewHolder(vh: WeatherForecastVH, position: Int)
    = vh.bind(mWeatherForecasts[position])

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> CURRENT_FORECAST
            else -> FUTURE_FORECAST
        }
    }

    fun updateWeatherForecasts(weatherForecasts :List<WeatherForecast>) {
        mWeatherForecasts = weatherForecasts
        notifyDataSetChanged()
    }


    abstract class WeatherForecastVH(v :ViewDataBinding) : RecyclerView.ViewHolder(v.root){
        abstract fun bind(weatherForecast: WeatherForecast)
    }

    class CurrentWeatherForecastVH(private val binding :WeatherForecastCurrentRvItemBinding)
        : WeatherForecastVH(binding) {

        override fun bind(weatherForecast: WeatherForecast) {
            binding.weatherForecast = weatherForecast

            binding.executePendingBindings()
        }

    }

    class FutureWeatherForecastVH(private val binding : WeatherForecastRvItemBinding)
        : WeatherForecastVH(binding) {

        override fun bind(weatherForecast: WeatherForecast) {
            binding.weatherForecast = weatherForecast

            binding.executePendingBindings()
        }

    }
}