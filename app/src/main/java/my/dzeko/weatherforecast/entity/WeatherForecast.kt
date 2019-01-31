package my.dzeko.weatherforecast.entity

import my.dzeko.weatherforecast.entity.mapping.WeatherForecastMapping


data class WeatherForecast(
    val dayAndMonth : String,
    val weatherForecastDetails :List<WeatherForecastDetail>,
    val city: City,
    var id :Long = 0
) {
    val minTemperature :Int
    val maxTemperature :Int
    val weather :String = weatherForecastDetails[0].weather.name

    init {
        var tempMin = weatherForecastDetails[0].temperature
        var tempMax = weatherForecastDetails[0].temperature

        weatherForecastDetails.forEach {
            it.weatherForecast = this

            val thisTemp = it.temperature
            when {
                tempMax < thisTemp -> tempMax = thisTemp
                tempMin > thisTemp -> tempMin = thisTemp
            }
        }

        minTemperature = tempMin
        maxTemperature = tempMax
    }

    constructor(mapping :WeatherForecastMapping,
                city :City,
                list :List<WeatherForecastDetail>) :this(
        mapping.dayAndMonth,
        list,
        city,
        mapping.id
    )
}