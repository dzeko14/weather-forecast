package my.dzeko.weatherforecast.entity.response

import com.google.gson.annotations.Expose

data class Coordinate(
    @Expose
    val lat :Double,

    @Expose
    val lon :Double
)