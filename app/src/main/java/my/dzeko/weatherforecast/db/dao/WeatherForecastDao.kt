package my.dzeko.weatherforecast.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import my.dzeko.weatherforecast.entity.mapping.WeatherForecastMapping

@Dao
interface WeatherForecastDao {
    @Insert
    fun insertWF(wf :WeatherForecastMapping) :Long

    @Delete
    fun deleteWF(wf :WeatherForecastMapping)

    @Query("Select * From 'WeatherForecastMapping' Where id = :id")
    fun getWFById(id :Long) :WeatherForecastMapping

    @Query("Select * From 'WeatherForecastMapping' Where city_id = :cityId")
    fun getWFsByCityId(cityId :Long) :List<WeatherForecastMapping>

    @Query("Delete From 'WeatherForecastMapping' Where date < :currDate")
    fun deleteWFsOlderThanDate(currDate :Long)
}