package my.dzeko.weatherforecast.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import my.dzeko.weatherforecast.repository.WeatherForecastDetailRepo
import my.dzeko.weatherforecast.viewmodel.MapsViewModel
import my.dzeko.weatherforecast.viewmodel.WeatherForecastDetailViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Suppress("UNCHECKED_CAST")
class WeatherForecastDetailViewModelFactory @Inject constructor(
    private val repo :WeatherForecastDetailRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherForecastDetailViewModel::class.java)) {
            return WeatherForecastDetailViewModel(repo) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}