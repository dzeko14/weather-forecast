package my.dzeko.weatherforecast.entity


data class WeatherForecastDay(
    val dayAndMonth : String,
    val weatherForecasts :List<WeatherForecast>,
    val id :Long = 0

)