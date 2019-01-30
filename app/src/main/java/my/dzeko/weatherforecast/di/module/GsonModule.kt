package my.dzeko.weatherforecast.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import java.text.DateFormat
import javax.inject.Singleton

@Module
class GsonModule {

    @Singleton
    @Provides
    fun providesGson() :Gson {
        return GsonBuilder().setDateFormat(DateFormat.LONG).create()
    }
}