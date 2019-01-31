package my.dzeko.weatherforecast.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import my.dzeko.weatherforecast.R
import my.dzeko.weatherforecast.adapter.WeatherForecastRVAdapter
import my.dzeko.weatherforecast.databinding.ActivityWeatherForecastBinding
import my.dzeko.weatherforecast.factory.WeatherForecastViewModelFactory
import my.dzeko.weatherforecast.viewmodel.WeatherForecastViewModel
import javax.inject.Inject

class WeatherForecastActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory :WeatherForecastViewModelFactory

    private lateinit var mViewModel : WeatherForecastViewModel

    private lateinit var mBinding :ActivityWeatherForecastBinding

    private lateinit var mAdapter :WeatherForecastRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather_forecast)

        mViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(WeatherForecastViewModel::class.java)

        setupRecyclerView()

        mViewModel.weatherForecast.observe(this,
            Observer { wf -> wf?.let{
                mAdapter.updateWeatherForecasts(wf)
                title = "${wf[0].city.name}, ${wf[0].city.country}"
            } })
    }

    private fun setupRecyclerView() {
        val layoutManger = LinearLayoutManager(this)
        mAdapter = WeatherForecastRVAdapter(emptyList())

        mBinding.weatherForecastRv.apply {
            layoutManager = layoutManger
            adapter = mAdapter
        }
    }


}
