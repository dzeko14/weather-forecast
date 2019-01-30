package my.dzeko.weatherforecast.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import my.dzeko.weatherforecast.R
import my.dzeko.weatherforecast.factory.WeatherForecastViewModelFactory
import my.dzeko.weatherforecast.viewmodel.WeatherForecastViewModel
import javax.inject.Inject

class WeatherForecastActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory :WeatherForecastViewModelFactory

    private lateinit var mViewModel : WeatherForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)

        mViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(WeatherForecastViewModel::class.java)

    }
}
