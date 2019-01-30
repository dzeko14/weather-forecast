package my.dzeko.weatherforecast.entity

import my.dzeko.weatherforecast.entity.response.WeatherForecastResponse
import java.util.*

data class WeatherForecast(
    val date : Date,
    val temperature :Double,
    val pressure :Double,
    val humidity :Double,
    val weather :List<Weather>,
    var city: City? = null,
    val id :Long = 0
) {
    constructor(response :WeatherForecastResponse)
    :this(
        Date(response.date * 1000L),
        response.infoResponse.temperature,
        response.infoResponse.pressure,
        response.infoResponse.humidity,
        response.weather
    )
}