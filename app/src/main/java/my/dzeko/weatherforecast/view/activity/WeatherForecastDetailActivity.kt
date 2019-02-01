package my.dzeko.weatherforecast.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import my.dzeko.weatherforecast.R
import my.dzeko.weatherforecast.adapter.WeatherForcastDetailRVAdapter
import my.dzeko.weatherforecast.databinding.ActivityWeatherForecastDetailBinding
import my.dzeko.weatherforecast.factory.WeatherForecastDetailViewModelFactory
import my.dzeko.weatherforecast.viewmodel.WeatherForecastDetailViewModel
import javax.inject.Inject

class WeatherForecastDetailActivity : DaggerAppCompatActivity() {
    private lateinit var mBinder :ActivityWeatherForecastDetailBinding
    private lateinit var mViewModel :WeatherForecastDetailViewModel
    private val mAdapter = WeatherForcastDetailRVAdapter(emptyList())

    @Inject lateinit var mViewModelFactory : WeatherForecastDetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinder = DataBindingUtil
            .setContentView(this, R.layout.activity_weather_forecast_detail)

        mViewModel = ViewModelProviders
            .of(this, mViewModelFactory).get(WeatherForecastDetailViewModel::class.java)

        mViewModel.detailWeatherForecasts.observe(this ,
            Observer {data -> data?.let {
                mAdapter.updateData(data)
                }
            })

        mBinder.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@WeatherForecastDetailActivity)
            adapter = mAdapter
        }

        startGettingData()
    }

    private fun startGettingData() {
        val weatherForecastId = intent.getLongExtra(WEATHER_FORECAST_ID, -1)
        mViewModel.getWeatherForecastDetails(weatherForecastId)
    }
}
