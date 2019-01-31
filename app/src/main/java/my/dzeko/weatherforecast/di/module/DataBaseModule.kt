package my.dzeko.weatherforecast.di.module

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import my.dzeko.weatherforecast.db.ApplicationDataBase
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun providesApplicationDataBase(appContext :Context) :ApplicationDataBase{
        return Room.databaseBuilder(appContext,
            ApplicationDataBase::class.java,
            "weather-forecast-db")
            .build()
    }
}