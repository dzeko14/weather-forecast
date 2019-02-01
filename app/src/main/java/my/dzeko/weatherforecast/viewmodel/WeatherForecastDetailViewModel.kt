package my.dzeko.weatherforecast.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import my.dzeko.weatherforecast.entity.WeatherForecastDetail
import my.dzeko.weatherforecast.repository.WeatherForecastDetailRepo
import javax.inject.Inject

class WeatherForecastDetailViewModel @Inject constructor(
    private val repo :WeatherForecastDetailRepo
) :ViewModel() {

    val detailWeatherForecasts = MutableLiveData<List<WeatherForecastDetail>>()

    fun getWeatherForecastDetails(weatherForecastId :Long) {
        repo.getWeatherForecastDetailsByWeatherForecast(weatherForecastId,
            object : WeatherForecastDetailsCallBack{
                override fun onDataReceived(list: List<WeatherForecastDetail>) {
                    detailWeatherForecasts.value = list
                }

                override fun onError(msg: String) {

                }
            })
    }

    interface WeatherForecastDetailsCallBack{
        fun onDataReceived(list :List<WeatherForecastDetail>)
        fun onError(msg :String)
    }
}