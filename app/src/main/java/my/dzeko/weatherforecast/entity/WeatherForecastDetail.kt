package my.dzeko.weatherforecast.entity

import my.dzeko.weatherforecast.entity.response.WeatherForecastResponse
import my.dzeko.weatherforecast.extension.getTimeString
import java.util.*

data class WeatherForecastDetail(
    val date : Date,
    val temperature :Int,
    val pressure :Int,
    val humidity :Int,
    val weather :List<Weather>,
    var weatherForecast: WeatherForecast? = null,
    val id :Long = 0
) {
    val time :String = date.getTimeString()
    val weatherName = weather[0].name
    val weatherDescription = weather[0].description

    constructor(response : WeatherForecastResponse)
    :this(
        Date(response.date * 1000L),
        response.infoResponse.temperature.toInt(),
        response.infoResponse.pressure.toInt(),
        response.infoResponse.humidity.toInt(),
        response.weather
    )
}