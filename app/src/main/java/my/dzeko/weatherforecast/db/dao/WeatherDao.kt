package my.dzeko.weatherforecast.db.dao

import android.arch.persistence.room.*
import my.dzeko.weatherforecast.entity.Weather

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWeather(weather :Weather) :Long

    @Delete
    fun deleteWeather(weather :Weather)

    @Query("Select * From 'Weather' Where id = :id")
    fun getWeatherById(id :Long) :Weather
}