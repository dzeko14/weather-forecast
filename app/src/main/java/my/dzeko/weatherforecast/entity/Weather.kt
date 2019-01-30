package my.dzeko.weatherforecast.entity

import com.google.gson.annotations.SerializedName

data class Weather(
    val id : Long,
    @SerializedName("main") val name :String,
    val description :String
)