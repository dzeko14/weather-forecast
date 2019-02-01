package my.dzeko.weatherforecast.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import my.dzeko.weatherforecast.entity.WeatherForecast
import my.dzeko.weatherforecast.repository.WeatherForecastRepository
import my.dzeko.weatherforecast.view.activity.CITY
import javax.inject.Inject

class WeatherForecastViewModel @Inject constructor(
    private val mRepo :WeatherForecastRepository
) : ViewModel() {

    val weatherForecast = MutableLiveData<List<WeatherForecast>>()
    val errorFlag = MutableLiveData<String>()

    fun handledError(){
        errorFlag.value = null
    }

    fun onIntentReceives(intent: Intent?) {
        if (intent == null) {
            startGettingWeatherForecastByLocation()
        } else {
            if(intent.hasExtra(CITY)) {
                val city = intent.getStringExtra(CITY)
                startGettingWeatherForecastByCity(city)
            } else {
                startGettingWeatherForecastByLocation()
            }
        }
    }

    private fun startGettingWeatherForecastByLocation(){
        mRepo.getWeatherForecast(object : WeatherForecastCallback{
            override fun onDataReceived(weatherForecastResponse: List<WeatherForecast>) {
                weatherForecast.value = weatherForecastResponse
            }

            override fun onError(msg :String) {
                errorFlag.value = msg
            }
        })
    }

    private fun startGettingWeatherForecastByCity(city :String){
        mRepo.getWeatherForecastByCity(city, object : WeatherForecastCallback{
            override fun onDataReceived(weatherForecastResponse: List<WeatherForecast>) {
                weatherForecast.value = weatherForecastResponse
            }

            override fun onError(msg :String) {
                errorFlag.value = msg
            }
        })
    }

    interface WeatherForecastCallback{
        fun onDataReceived(weatherForecastResponse: List<WeatherForecast>)
        fun onError(msg :String)
    }
}