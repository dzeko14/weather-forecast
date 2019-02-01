package my.dzeko.weatherforecast.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_weather_forecast.view.*
import my.dzeko.weatherforecast.R
import my.dzeko.weatherforecast.adapter.WeatherForecastRVAdapter
import my.dzeko.weatherforecast.databinding.ActivityWeatherForecastBinding
import my.dzeko.weatherforecast.factory.WeatherForecastViewModelFactory
import my.dzeko.weatherforecast.viewmodel.WeatherForecastViewModel
import javax.inject.Inject

const val WEATHER_FORECAST_ID = "weather_forecast_id"

class WeatherForecastActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory :WeatherForecastViewModelFactory

    private lateinit var mViewModel : WeatherForecastViewModel

    private lateinit var mBinding :ActivityWeatherForecastBinding

    private val mAdapter = WeatherForecastRVAdapter(emptyList(), object : OnWeatherForecastClickListener{
            override fun onWeatherClick(weatherForecastId: Long) {
                val intent = Intent(this@WeatherForecastActivity,
                    WeatherForecastDetailActivity::class.java)

                intent.putExtra(WEATHER_FORECAST_ID, weatherForecastId)

                this@WeatherForecastActivity.startActivity(intent)
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather_forecast)

        mViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(WeatherForecastViewModel::class.java)

        setupRecyclerView()

        mViewModel.weatherForecast.observe(this,
            Observer { wf -> wf?.let{
                if (wf.size > 0) {
                    mAdapter.updateWeatherForecasts(wf)
                    title = "${wf[0].city.name}, ${wf[0].city.country}"
                    mBinding.isDataFound = true
                }
                else{
                    mBinding.isDataFound = false
                }
            }
                mBinding.isLoading = false
                mBinding.executePendingBindings()
            })

        mViewModel.errorFlag.observe(this,
            Observer { msg ->
                if (msg != null) {
                    mBinding.isDataFound = false
                    mViewModel.handledError()
                    Toast
                        .makeText(this@WeatherForecastActivity, msg, Toast.LENGTH_LONG)
                        .show()
                    mBinding.isLoading = false
                    mBinding.executePendingBindings()
                }
            })

        mBinding.isDataFound = true
        mBinding.isLoading = true

        mBinding.executePendingBindings()

        mViewModel.onIntentReceives(intent)
    }

    private fun setupRecyclerView() {
        val layoutManger = LinearLayoutManager(this)
        mBinding.weatherForecastRv.apply {
            layoutManager = layoutManger
            adapter = mAdapter
        }
    }

    interface OnWeatherForecastClickListener{
        fun onWeatherClick(weatherForecastId :Long)
    }
}
