package my.dzeko.weatherforecast.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import my.dzeko.weatherforecast.entity.WeatherForecast
import my.dzeko.weatherforecast.entity.WeatherForecastDay
import my.dzeko.weatherforecast.entity.response.WeatherForecastResponse
import my.dzeko.weatherforecast.repository.WeatherForecastRepository
import javax.inject.Inject

class WeatherForecastViewModel @Inject constructor(
    private val mRepo :WeatherForecastRepository
) : ViewModel() {

    val weatherForecast = MutableLiveData<List<WeatherForecastDay>>()

    init {
        mRepo.getWeatherForecast(object : WeatherForecastCallback{
            override fun onDataReceived(weatherForecastResponse: List<WeatherForecastDay>) {
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
        fun onDataReceived(weatherForecastResponse: List<WeatherForecastDay>)
        fun onError()
    }
}