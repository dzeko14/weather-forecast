package my.dzeko.weatherforecast.model

import android.content.Context
import android.preference.PreferenceManager
import com.google.android.gms.maps.model.LatLng
import my.dzeko.weatherforecast.viewmodel.MapsViewModel
import javax.inject.Inject
import javax.inject.Singleton

private const val MARKER_LONG = "marker-long"
private const val MARKER_LAT = "marker-lat"

@Singleton
class AppPreferencesManager @Inject constructor(appContext: Context) {
    private val mSharedPreferences = PreferenceManager
        .getDefaultSharedPreferences(appContext)

    fun getMarkerLongLat() :LatLng{
        val long = mSharedPreferences.getFloat(MARKER_LONG, 30.52f)
        val lat = mSharedPreferences.getFloat(MARKER_LAT, 50.45f)

        return LatLng(lat.toDouble(), long.toDouble())
    }

    fun saveMarkerLongLat(longLat :LatLng) {
        val editor = mSharedPreferences.edit()

        editor.putFloat(MARKER_LAT, longLat.latitude.toFloat())
        editor.putFloat(MARKER_LONG, longLat.longitude.toFloat())

        editor.apply()
    }
}