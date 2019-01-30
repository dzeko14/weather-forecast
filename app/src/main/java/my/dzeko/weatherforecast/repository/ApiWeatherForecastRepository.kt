package my.dzeko.weatherforecast.repository

import android.annotation.SuppressLint
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import my.dzeko.weatherforecast.api.service.WeatherForecastService
import my.dzeko.weatherforecast.entity.City
import my.dzeko.weatherforecast.entity.WeatherForecast
import my.dzeko.weatherforecast.entity.WeatherForecastDay
import my.dzeko.weatherforecast.entity.response.WeatherDataResponse
import my.dzeko.weatherforecast.extension.getDayAndMonthString
import my.dzeko.weatherforecast.extension.isSameDay
import my.dzeko.weatherforecast.repository.interfaces.IWeatherForecastRepository
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiWeatherForecastRepository @Inject constructor(
    private val mWeatherForecastService: WeatherForecastService
) : IWeatherForecastRepository {

    @SuppressLint("CheckResult")
    override fun getWeatherForecastByLocation(location: LatLng): Single<List<WeatherForecastDay>> {
        val response = mWeatherForecastService
            .getWeatherForecastByLocation(location.longitude, location.latitude)

        return adaptCityAndWeatherResponseObjects(response)
    }


    @SuppressLint("CheckResult")
    private fun adaptCityAndWeatherResponseObjects(response :Single<WeatherDataResponse>)
    : Single<List<WeatherForecastDay>> {
        return response.flatMap<List<WeatherForecastDay>> { weatherDataRespons ->
                val city = City(weatherDataRespons.cityResponse)

                var weatherForecastList = mutableListOf<WeatherForecast>()
                var date = Date()
                val weatherForecastDayList = mutableListOf<WeatherForecastDay>()

                for (wf in weatherDataRespons.list){
                    val newWF = WeatherForecast(wf).apply { this.city = city }

                    if (date.isSameDay(newWF.date)){

                        weatherForecastList.add(newWF)

                    } else {

                        if (weatherForecastList.size != 0){
                            weatherForecastDayList.add(
                                WeatherForecastDay(
                                    date.getDayAndMonthString(),
                                    weatherForecastList
                                )
                            )
                        }

                        date = newWF.date
                        weatherForecastList = mutableListOf()
                        weatherForecastList.add(newWF)
                    }
                }

                return@flatMap Single.just(weatherForecastDayList)
        }
    }
}