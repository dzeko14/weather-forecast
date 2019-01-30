package my.dzeko.weatherforecast.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class WeatherForecast(
    @SerializedName("dt") val date : Date,
    @SerializedName("main") val info :WeatherMainInfo,
    val weather :List<Weather>
    )