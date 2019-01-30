package my.dzeko.weatherforecast.api.service

import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherForecastService {
    @GET("/data/2.5/forecast?lat={lat}&lon={lon}")
    fun getWeatherForecastByLocation(@Path("lon") lon :Float,
                                     @Path("lat") lat :Float)
}