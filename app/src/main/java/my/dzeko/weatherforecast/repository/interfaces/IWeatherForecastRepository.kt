package my.dzeko.weatherforecast.repository.interfaces

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import my.dzeko.weatherforecast.entity.WeatherForecast

interface IWeatherForecastRepository {
    fun getWeatherForecastByLocation(location :LatLng) : Single<List<WeatherForecast>>
    fun getWeatherForecastByCity(city :String) : Single<List<WeatherForecast>>
}