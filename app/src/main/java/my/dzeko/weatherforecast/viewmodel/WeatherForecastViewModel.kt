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
    val errorFlag = MutableLiveData<String>()

    init {
        mRepo.getWeatherForecast(object : WeatherForecastCallback{
            override fun onDataReceived(weatherForecastResponse: List<WeatherForecast>) {
                weatherForecast.value = weatherForecastResponse
            }

            override fun onError(msg :String) {
                errorFlag.value = msg
            }
        })
    }

    fun handledError(){
        errorFlag.value = null
    }

    interface WeatherForecastCallback{
        fun onDataReceived(weatherForecastResponse: List<WeatherForecast>)
        fun onError(msg :String)
    }
}