package my.dzeko.weatherforecast.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityResponse(
    @Expose
    val id : Long,

    @Expose
    val name :String,

    @Expose
    val country :String,

    @SerializedName("coord")
    @Expose
    val coordinate: Coordinate
   )