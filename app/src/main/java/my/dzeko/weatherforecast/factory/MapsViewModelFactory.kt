package my.dzeko.weatherforecast.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import my.dzeko.weatherforecast.repository.LocationRepository
import my.dzeko.weatherforecast.viewmodel.MapsViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class MapsViewModelFactory @Inject constructor(
    private val mLocationRepository: LocationRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
            return MapsViewModel(mLocationRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}