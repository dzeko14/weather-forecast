package my.dzeko.weatherforecast.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import my.dzeko.weatherforecast.application.WeatherForecastApp
import my.dzeko.weatherforecast.di.module.*
import my.dzeko.weatherforecast.factory.WeatherForecastViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MapsActivityModule::class,
        GsonModule::class,
        OkHttpClientModule::class,
        RetrofitModule::class,
        WeatherForecastActivityModule::class,
        DaoModule::class,
        DataBaseModule::class,
        WeatherForecastDetailActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<WeatherForecastApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WeatherForecastApp>()

}