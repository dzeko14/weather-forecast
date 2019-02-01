package my.dzeko.weatherforecast.db.dao

import android.arch.persistence.room.*
import my.dzeko.weatherforecast.entity.mapping.WeatherForecastMapping
import java.util.*

@Dao
interface WeatherForecastDao {
    @Insert()
    fun insertWF(wf :WeatherForecastMapping) :Long

    @Delete
    fun deleteWF(wf :WeatherForecastMapping)

    @Query("Select * From 'WeatherForecastMapping' Where id = :id")
    fun getWFById(id :Long) :WeatherForecastMapping

    @Query("Select * From 'WeatherForecastMapping' Where city_id = :cityId")
    fun getWFsByCityId(cityId :Long) :List<WeatherForecastMapping>

    @Query("Delete From 'WeatherForecastMapping' Where date < :currDate")
    fun deleteWFsOlderThanDate(currDate :Long)

    @Query("Select * From 'WeatherForecastMapping' Where date = :date And city_id = :cityId")
    fun getWFByCityIdAndDate(cityId: Long, date: Date) :WeatherForecastMapping?
}