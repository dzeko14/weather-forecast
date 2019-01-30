package my.dzeko.weatherforecast.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import my.dzeko.weatherforecast.repository.WeatherForecastRepository
import my.dzeko.weatherforecast.viewmodel.WeatherForecastViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class WeatherForecastViewModelFactory @Inject constructor(
    private val repo :WeatherForecastRepository
) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherForecastViewModel::class.java)) {
            return WeatherForecastViewModel(repo) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}