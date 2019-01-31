package my.dzeko.weatherforecast.repository

import android.annotation.SuppressLint
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import my.dzeko.weatherforecast.api.service.WeatherForecastService
import my.dzeko.weatherforecast.entity.City
import my.dzeko.weatherforecast.entity.WeatherForecastDetail
import my.dzeko.weatherforecast.entity.WeatherForecast
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
    override fun getWeatherForecastByLocation(location: LatLng): Single<List<WeatherForecast>> {
        val response = mWeatherForecastService
            .getWeatherForecastByLocation(location.longitude, location.latitude)

        return adaptCityAndWeatherResponseObjects(response)
    }


    @SuppressLint("CheckResult")
    private fun adaptCityAndWeatherResponseObjects(response :Single<WeatherDataResponse>)
    : Single<List<WeatherForecast>> {
        return response.flatMap<List<WeatherForecast>> { weatherDataRespons ->
                val city = City(weatherDataRespons.cityResponse)

                var weatherForecastList = mutableListOf<WeatherForecastDetail>()
                var date = Date()
                val weatherForecastDayList = mutableListOf<WeatherForecast>()

                for (wfDetail in weatherDataRespons.list){
                    val newWFDetail = WeatherForecastDetail(wfDetail)

                    if (date.isSameDay(newWFDetail.date)){

                        weatherForecastList.add(newWFDetail)

                    } else {

                        if (weatherForecastList.size != 0){
                            val wf =
                                WeatherForecast(
                                    date.getDayAndMonthString(),
                                    weatherForecastList,
                                    city)
                            weatherForecastDayList.add(wf)
                        }

                        date = newWFDetail.date
                        weatherForecastList = mutableListOf()
                        weatherForecastList.add(newWFDetail)
                    }
                }

                return@flatMap Single.just(weatherForecastDayList)
        }
    }
}