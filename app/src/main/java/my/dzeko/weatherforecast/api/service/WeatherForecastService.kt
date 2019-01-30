package my.dzeko.weatherforecast.api.service

import io.reactivex.Observable
import io.reactivex.Single
import my.dzeko.weatherforecast.entity.response.WeatherDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastService {
    @GET("/data/2.5/forecast")
    fun getWeatherForecastByLocation(@Query("lon") lon :Double,
                                     @Query("lat") lat :Double)
            : Single<WeatherDataResponse>
}