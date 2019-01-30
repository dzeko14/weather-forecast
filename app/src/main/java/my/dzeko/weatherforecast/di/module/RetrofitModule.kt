package my.dzeko.weatherforecast.di.module

import dagger.Module
import dagger.Provides
import my.dzeko.weatherforecast.api.service.WeatherForecastService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun providesWeatherForecastService(okHttpClient :OkHttpClient) : WeatherForecastService{
        val retrofit = Retrofit.Builder()
            .baseUrl("api.openweathermap.org")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(WeatherForecastService::class.java)
    }

}