package my.dzeko.weatherforecast.entity.mapping

import android.arch.persistence.room.*
import my.dzeko.weatherforecast.entity.City
import my.dzeko.weatherforecast.entity.WeatherForecast
import java.util.*

@Entity(
    foreignKeys = [ForeignKey(
        entity =  City::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("city_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
    Index(value = arrayOf("city_id"))]
)
data class WeatherForecastMapping(
    val dayAndMonth : String,
    val date :Date,
    @ColumnInfo(name = "city_id") val cityId: Long,
    @PrimaryKey(autoGenerate = true) val id :Long = 0
) {
    @Ignore constructor(wf :WeatherForecast) :this(
        wf.dayAndMonth,
        wf.weatherForecastDetails.first().date,
        wf.city.id,
        wf.id
    )
}