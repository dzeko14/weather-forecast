package my.dzeko.weatherforecast.db.dao

import android.arch.persistence.room.*
import my.dzeko.weatherforecast.entity.City

@Dao
interface CityDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city : City) :Long

    @Delete
    fun deleteCity(city: City)

    @Query("Select * From 'City' Where id = :id")
    fun getCityById(id :Long) :City?

    @Query("Select * From 'City' Where ABS(longitude - :lon) + ABS(latitude - :lat) < 1 LIMIT 1")
    fun getCityByLocation(lon :Double, lat :Double) :City?
}