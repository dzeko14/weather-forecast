package my.dzeko.weatherforecast.di.module

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import my.dzeko.weatherforecast.factory.MapsViewModelFactory
import my.dzeko.weatherforecast.repository.LocationRepository
import my.dzeko.weatherforecast.view.activity.MapsActivity
import javax.inject.Singleton

@Module
abstract class MapsActivityModule {

    @ContributesAndroidInjector
    abstract fun mapsActivity() :MapsActivity

    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun providesMapsViewModelFactory(locationRepository: LocationRepository) :MapsViewModelFactory{
            return MapsViewModelFactory(locationRepository)
        }
    }
}