package my.dzeko.weatherforecast.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import my.dzeko.weatherforecast.entity.Weather

data class WeatherForecastResponse(
    @SerializedName("dt")
    @Expose
    val date : Long,

    @SerializedName("main")
    @Expose
    val infoResponse : WeatherMainInfoResponse,

    @Expose
    val weather :List<Weather>,

    var cityResponse: CityResponse? = null
    )