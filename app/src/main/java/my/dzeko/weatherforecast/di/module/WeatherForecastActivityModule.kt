package my.dzeko.weatherforecast.di.module

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import my.dzeko.weatherforecast.factory.WeatherForecastViewModelFactory
import my.dzeko.weatherforecast.repository.WeatherForecastRepository
import my.dzeko.weatherforecast.view.activity.WeatherForecastActivity
import javax.inject.Singleton

@Module
abstract class WeatherForecastActivityModule {

    @ContributesAndroidInjector
    abstract fun getActivity() :WeatherForecastActivity

    @Module
    companion object {
        @Singleton
        @JvmStatic
        @Provides
        fun providesWeatherForecstViewModelFactory(repo :WeatherForecastRepository)
        :WeatherForecastViewModelFactory{
            return WeatherForecastViewModelFactory(repo)
        }
    }
}