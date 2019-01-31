package my.dzeko.weatherforecast.entity.mapping

import android.arch.persistence.room.*
import my.dzeko.weatherforecast.entity.Weather
import my.dzeko.weatherforecast.entity.WeatherForecastDetail
import java.util.*

@Entity(
    foreignKeys = [ForeignKey(
        entity = Weather::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("weather_id"),
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = WeatherForecastMapping::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("weather_forecast_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index(value = arrayOf("weather_id")),
        Index(value = arrayOf("weather_forecast_id"))
    ]
)
data class WeatherForecastDetailMapping(
    val date : Date,
    val temperature :Int,
    val pressure :Int,
    val humidity :Int,
    @ColumnInfo(name = "weather_id") val weatherId : Long,
    @ColumnInfo(name = "weather_forecast_id") val weatherForecastId: Long,
    @PrimaryKey(autoGenerate = true) val id :Long
) {
    @Ignore constructor(wfd :WeatherForecastDetail) :this(
        wfd.date,
        wfd.temperature,
        wfd.pressure,
        wfd.humidity,
        wfd.weather.id,
        wfd.weatherForecast!!.id,
        wfd.id
    )
}