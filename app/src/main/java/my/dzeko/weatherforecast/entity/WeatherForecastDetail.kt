package my.dzeko.weatherforecast.entity

import my.dzeko.weatherforecast.entity.mapping.WeatherForecastDetailMapping
import my.dzeko.weatherforecast.entity.response.WeatherForecastResponse
import my.dzeko.weatherforecast.extension.getTimeString
import java.util.*

data class WeatherForecastDetail(
    val date : Date,
    val temperature :Int,
    val pressure :Int,
    val humidity :Int,
    val weather :Weather,
    var weatherForecast: WeatherForecast? = null,
    var id :Long = 0
) {
    val time :String = date.getTimeString()
    val weatherName = weather.name
    val weatherDescription = weather.description

    constructor(response : WeatherForecastResponse)
    :this(
        Date(response.date * 1000L),
        response.infoResponse.temperature.toInt(),
        response.infoResponse.pressure.toInt(),
        response.infoResponse.humidity.toInt(),
        response.weather[0]
    )

    constructor(mapping: WeatherForecastDetailMapping,
                weather :Weather,
                weatherForecast: WeatherForecast?) :this(
        mapping.date,
        mapping.temperature,
        mapping.pressure,
        mapping.humidity,
        weather,
        weatherForecast,
        mapping.id
    )
}