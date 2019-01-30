package my.dzeko.weatherforecast.repository

import com.google.android.gms.maps.model.LatLng
import my.dzeko.weatherforecast.manager.AppPreferencesManager
import my.dzeko.weatherforecast.viewmodel.MapsViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(
    private val mAppPreferencesManager: AppPreferencesManager
) {
    fun getMarkLongLat(callback : MapsViewModel.OnMarkerLatLongReceived) {
        val location = mAppPreferencesManager.getMarkerLongLat()
        callback.onDataReceived(location)
    }

    fun saveMarkLongLat(location :LatLng){
        mAppPreferencesManager.saveMarkerLongLat(location)
    }
}