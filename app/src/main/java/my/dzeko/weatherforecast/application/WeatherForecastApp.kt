package my.dzeko.weatherforecast.application

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import my.dzeko.weatherforecast.di.component.DaggerAppComponent

class WeatherForecastApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerAppComponent.builder().create(this)
    }
}