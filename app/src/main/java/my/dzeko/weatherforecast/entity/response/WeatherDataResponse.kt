package my.dzeko.weatherforecast.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherDataResponse(
    @Expose
    val list : List<WeatherForecastResponse>,

    @SerializedName("city")
    @Expose
    val cityResponse : CityResponse
)