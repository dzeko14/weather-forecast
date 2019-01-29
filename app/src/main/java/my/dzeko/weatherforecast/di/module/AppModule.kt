package my.dzeko.weatherforecast.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import my.dzeko.weatherforecast.application.WeatherForecastApp

@Module
class AppModule {

    @Provides
    fun providesApplicationContext(app :WeatherForecastApp) : Context{
        return app.applicationContext
    }

}