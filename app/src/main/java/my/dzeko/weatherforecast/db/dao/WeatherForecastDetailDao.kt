package my.dzeko.weatherforecast.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import my.dzeko.weatherforecast.entity.mapping.WeatherForecastDetailMapping

@Dao
interface WeatherForecastDetailDao {
    @Insert
    fun insertWeatherForecastDetail(wfd : WeatherForecastDetailMapping) :Long

    @Delete
    fun deleteWFD(wfd : WeatherForecastDetailMapping)

    @Query("Select * From 'WeatherForecastDetailMapping' Where id = :id")
    fun getWFDById(id :Long) :WeatherForecastDetailMapping

    @Query("Select * From 'WeatherForecastDetailMapping' Where weather_forecast_id = :weatherForecastId")
    fun getWFDByWeatherForecastId(weatherForecastId :Long) :List<WeatherForecastDetailMapping>

    @Query("Delete From 'WeatherForecastDetailMapping' Where date < :currDate")
    fun deleteWFDsOlderThanDate(currDate :Long)
}