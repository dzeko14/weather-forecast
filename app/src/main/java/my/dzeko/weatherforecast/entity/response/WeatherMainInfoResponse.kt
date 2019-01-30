package my.dzeko.weatherforecast.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherMainInfoResponse(
    @Expose
    @SerializedName("temp")
    val temperature :Double,

    @Expose
    val pressure :Double,

    @Expose
    val humidity :Double
)