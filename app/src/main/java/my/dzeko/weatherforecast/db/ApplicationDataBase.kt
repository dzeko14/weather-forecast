package my.dzeko.weatherforecast.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import my.dzeko.weatherforecast.converter.DateConverter
import my.dzeko.weatherforecast.db.dao.CityDao
import my.dzeko.weatherforecast.db.dao.WeatherDao
import my.dzeko.weatherforecast.db.dao.WeatherForecastDao
import my.dzeko.weatherforecast.db.dao.WeatherForecastDetailDao
import my.dzeko.weatherforecast.entity.City
import my.dzeko.weatherforecast.entity.Weather
import my.dzeko.weatherforecast.entity.mapping.WeatherForecastDetailMapping
import my.dzeko.weatherforecast.entity.mapping.WeatherForecastMapping

@Database(entities = [
    WeatherForecastMapping::class,
    WeatherForecastDetailMapping::class,
    City::class,
    Weather::class
],
    version =  1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class ApplicationDataBase :RoomDatabase() {
    abstract fun cityDao() :CityDao
    abstract fun weatherDao() :WeatherDao
    abstract fun weatherForecastDao() :WeatherForecastDao
    abstract fun weatherForecastDetailDao() :WeatherForecastDetailDao
}