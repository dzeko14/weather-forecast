package my.dzeko.weatherforecast.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import my.dzeko.weatherforecast.application.WeatherForecastApp
import my.dzeko.weatherforecast.di.module.AppModule
import my.dzeko.weatherforecast.di.module.MapsActivityModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MapsActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<WeatherForecastApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WeatherForecastApp>()

}