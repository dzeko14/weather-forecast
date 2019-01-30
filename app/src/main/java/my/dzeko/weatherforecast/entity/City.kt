package my.dzeko.weatherforecast.entity

import com.google.gson.annotations.SerializedName

data class City(
    val id : Long,
    val name :String,
    val country :String,
    @SerializedName("coord") val coordinate : Coordinate
   )