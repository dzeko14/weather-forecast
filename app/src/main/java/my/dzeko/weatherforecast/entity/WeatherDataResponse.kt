package my.dzeko.weatherforecast.entity

data class WeatherDataResponse(
    val list : List<WeatherForecast>,
    val city : City
)