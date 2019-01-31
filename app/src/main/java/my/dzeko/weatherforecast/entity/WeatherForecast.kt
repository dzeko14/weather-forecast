package my.dzeko.weatherforecast.entity


data class WeatherForecast(
    val dayAndMonth : String,
    val weatherForecastDetails :List<WeatherForecastDetail>,
    val city: City,
    val id :Long = 0
) {
    val minTemperature :Int
    val maxTemperature :Int
    val weather :String

    init {
        weather = weatherForecastDetails[0].weather[0].name
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

        minTemperature = tempMin.toInt()
        maxTemperature = tempMax.toInt()
    }
}