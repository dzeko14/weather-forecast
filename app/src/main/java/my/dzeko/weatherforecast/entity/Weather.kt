package my.dzeko.weatherforecast.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Weather(
    @Expose
    val id : Long,

    @Expose
    @SerializedName("main")
    val name :String,

    @Expose
    val description :String
)