package my.dzeko.weatherforecast.di.module

import dagger.Module
import dagger.Provides
import my.dzeko.weatherforecast.db.ApplicationDataBase
import my.dzeko.weatherforecast.db.dao.CityDao
import my.dzeko.weatherforecast.db.dao.WeatherDao
import my.dzeko.weatherforecast.db.dao.WeatherForecastDao
import my.dzeko.weatherforecast.db.dao.WeatherForecastDetailDao
import javax.inject.Singleton

@Module
class DaoModule {
    @Provides
    @Singleton
    fun providesCityDao(db :ApplicationDataBase) :CityDao{
        return db.cityDao()
    }

    @Provides
    @Singleton
    fun providesWeatherDao(db :ApplicationDataBase) :WeatherDao{
        return db.weatherDao()
    }

    @Provides
    @Singleton
    fun providesWeatherForecastDao(db :ApplicationDataBase) : WeatherForecastDao {
        return db.weatherForecastDao()
    }

    @Provides
    @Singleton
    fun providesWeatherForecastDetailDao(db :ApplicationDataBase) : WeatherForecastDetailDao {
        return db.weatherForecastDetailDao()
    }
}