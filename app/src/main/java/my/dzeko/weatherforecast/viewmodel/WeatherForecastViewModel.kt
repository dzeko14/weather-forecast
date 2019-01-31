package my.dzeko.weatherforecast.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import my.dzeko.weatherforecast.entity.WeatherForecast
import my.dzeko.weatherforecast.repository.WeatherForecastRepository
import javax.inject.Inject

class WeatherForecastViewModel @Inject constructor(
    private val mRepo :WeatherForecastRepository
) : ViewModel() {

    val weatherForecast = MutableLiveData<List<WeatherForecast>>()

    init {
        mRepo.getWeatherForecast(object : WeatherForecastCallback{
            override fun onDataReceived(weatherForecastResponse: List<WeatherForecast>) {
                for (i in weatherForecastResponse){
                    weatherForecast.value = weatherForecastResponse
                }
            }

            override fun onError() {
                TODO("not implemented")
            }
        })
    }

    interface WeatherForecastCallback{
        fun onDataReceived(weatherForecastResponse: List<WeatherForecast>)
        fun onError()
    }
}