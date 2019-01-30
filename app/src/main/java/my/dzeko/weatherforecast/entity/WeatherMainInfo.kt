package my.dzeko.weatherforecast.entity

import com.google.gson.annotations.SerializedName

data class WeatherMainInfo(
    @SerializedName("temp") val temperature :Double,
    val pressure :Double,
    val humidity :Double
)