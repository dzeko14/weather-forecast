package my.dzeko.weatherforecast.di.module

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import my.dzeko.weatherforecast.factory.WeatherForecastDetailViewModelFactory
import my.dzeko.weatherforecast.repository.WeatherForecastDetailRepo
import my.dzeko.weatherforecast.view.activity.WeatherForecastDetailActivity
import javax.inject.Singleton

@Module
abstract class WeatherForecastDetailActivityModule(){
    @ContributesAndroidInjector
    abstract fun weatherForecastDetailActivity() : WeatherForecastDetailActivity

    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun providesWeatherForecastDetailViewModelFactory(repo :WeatherForecastDetailRepo) : WeatherForecastDetailViewModelFactory{
            return WeatherForecastDetailViewModelFactory(repo)
        }
    }
}