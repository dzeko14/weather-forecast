package my.dzeko.weatherforecast.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.DaggerAppCompatActivity
import my.dzeko.weatherforecast.R
import my.dzeko.weatherforecast.factory.MapsViewModelFactory
import my.dzeko.weatherforecast.viewmodel.MapsViewModel
import javax.inject.Inject

class MapsActivity : DaggerAppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mViewModel :MapsViewModel
    private lateinit var mMarker :Marker

    @Inject lateinit var mViewModelFactory: MapsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MapsViewModel::class.java)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMarker = mMap.addMarker(MarkerOptions().position(LatLng(0.0,0.0)))

        mMap.setOnMapClickListener { location ->
            mViewModel.onUserMarkerChanged(location)
        }

        mViewModel.markerLocation.observe(this, Observer { location->
            location?.let {
                mMarker.position = location
            }

        })

        mViewModel.markerLocation.value?.let { initialLocation ->
            mMarker.position = initialLocation
            mMap.moveCamera(CameraUpdateFactory.newLatLng(initialLocation))
        }
    }


}
