package my.dzeko.weatherforecast.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import my.dzeko.weatherforecast.repository.LocationRepository
import javax.inject.Inject

class MapsViewModel @Inject constructor(
    private val mLocationRepository: LocationRepository
) : ViewModel() {

    val markerLocation = MutableLiveData<LatLng>()

    init {
        mLocationRepository.getMarkLongLat(object : OnMarkerLatLongReceived{
            override fun onDataReceived(markerLocation: LatLng) {
                this@MapsViewModel.markerLocation.value = markerLocation
            }
        })
    }

    fun onUserMarkerChanged(location :LatLng) {
        markerLocation.value = location
        mLocationRepository.saveMarkLongLat(location)
    }

    interface OnMarkerLatLongReceived{
        fun onDataReceived(markerLocation :LatLng)
    }
}