package my.dzeko.weatherforecast.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

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

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MapsViewModel::class.java)

        findViewById<Button>(R.id.weather_forecast_button).setOnClickListener {
            val intent = Intent(this, WeatherForecastActivity::class.java)
            startActivity(intent)
        }
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



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.maps_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        startActivity(Intent(this, SearchActivity::class.java))
        return true
    }

}
