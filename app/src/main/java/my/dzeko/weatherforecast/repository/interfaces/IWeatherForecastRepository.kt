package my.dzeko.weatherforecast.repository.interfaces

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import my.dzeko.weatherforecast.entity.WeatherForecastDay

interface IWeatherForecastRepository {
    fun getWeatherForecastByLocation(location :LatLng) : Single<List<WeatherForecastDay>>
}