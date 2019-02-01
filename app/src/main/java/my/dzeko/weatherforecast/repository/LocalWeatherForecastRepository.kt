package my.dzeko.weatherforecast.repository

import android.annotation.SuppressLint
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Completable
import io.reactivex.Single
import my.dzeko.weatherforecast.db.ApplicationDataBase
import my.dzeko.weatherforecast.entity.City
import my.dzeko.weatherforecast.entity.WeatherForecast
import my.dzeko.weatherforecast.entity.WeatherForecastDetail
import my.dzeko.weatherforecast.entity.mapping.WeatherForecastDetailMapping
import my.dzeko.weatherforecast.entity.mapping.WeatherForecastMapping
import my.dzeko.weatherforecast.repository.interfaces.IWeatherForecastRepository
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalWeatherForecastRepository @Inject constructor(
    db :ApplicationDataBase
) :IWeatherForecastRepository {

    private val cityDao = db.cityDao()
    private val weatherDao = db.weatherDao()
    private val weatherForecastDao = db.weatherForecastDao()
    private val weatherForecastDetailDao = db.weatherForecastDetailDao()

    @SuppressLint("CheckResult")
    override fun getWeatherForecastByLocation(location: LatLng)
            : Single<List<WeatherForecast>> {
         return Single.fromCallable {
             val city = cityDao.getCityByLocation(location.longitude, location.latitude)
                 ?: return@fromCallable emptyList<WeatherForecast>()

             cleanOldData()
             return@fromCallable mapData(city)
        }
    }

    private fun mapData(city :City) :List<WeatherForecast> {
        val weatherForecastMappings = weatherForecastDao.getWFsByCityId(city.id)
        val weatherForecasts = mutableListOf<WeatherForecast>()

        for (wfMapping in weatherForecastMappings) {
            val wfdsMapping = weatherForecastDetailDao.getWFDByWeatherForecastId(wfMapping.id)
            val wfds = mutableListOf<WeatherForecastDetail>()

            wfdsMapping.forEach { mapping ->
                val weather = weatherDao.getWeatherById(mapping.weatherId)
                wfds.add(WeatherForecastDetail(mapping, weather, null))
            }
            val wf = WeatherForecast(
                wfMapping,
                city,
                wfds
            )
            wfds.forEach { wfd -> wfd.weatherForecast = wf }
            weatherForecasts.add(wf)
        }
        return weatherForecasts
    }

    private fun cleanOldData() {
        val currentDate = Date()
        weatherForecastDao.deleteWFsOlderThanDate(currDate = currentDate.time)
    }

    @SuppressLint("CheckResult")
    fun saveWeatherForecasts(weatherForecasts: List<WeatherForecast>) :Single<List<WeatherForecast>> {
         return Single.fromCallable {
            val city = weatherForecasts.first().city
             if (cityDao.getCityById(city.id) == null) {
                 cityDao.insertCity(city)
             }


            for (weatherForecast in weatherForecasts) {
                weatherForecast.weatherForecastDetails.forEach { wfd ->
                    weatherDao.insertWeather(wfd.weather)
                }

                val wfdMapper = weatherForecastDao
                    .getWFByCityIdAndDate(weatherForecast.city.id,
                        weatherForecast.
                            weatherForecastDetails.first()
                            .date)
                if(wfdMapper != null) {
                    weatherForecast.id = wfdMapper.id
                    continue
                }

                val wfMapper = WeatherForecastMapping(weatherForecast)
                weatherForecast.id = weatherForecastDao.insertWF(wfMapper)

                weatherForecast.weatherForecastDetails.forEach { wfd ->
                    wfd.id = weatherForecastDetailDao.insertWeatherForecastDetail(
                        WeatherForecastDetailMapping(wfd)
                    )
                }
            }

            return@fromCallable weatherForecasts
        }
    }

    @SuppressLint("CheckResult")
    fun saveWeatherForecasts(weatherForecastsSingle :Single<List<WeatherForecast>>) :Single<List<WeatherForecast>> {
       return  weatherForecastsSingle.flatMap {wfs ->
            saveWeatherForecasts(wfs)
        }
    }

    override fun getWeatherForecastByCity(cityName: String): Single<List<WeatherForecast>> {
        return Single.fromCallable {
            val city = cityDao.getCityByName(cityName)
                ?: return@fromCallable emptyList<WeatherForecast>()

            cleanOldData()
            return@fromCallable mapData(city)
        }
    }
}