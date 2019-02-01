package my.dzeko.weatherforecast.repository

import android.annotation.SuppressLint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import my.dzeko.weatherforecast.db.ApplicationDataBase
import my.dzeko.weatherforecast.entity.WeatherForecastDetail
import my.dzeko.weatherforecast.viewmodel.WeatherForecastDetailViewModel
import javax.inject.Inject

class WeatherForecastDetailRepo @Inject constructor(
    db :ApplicationDataBase
) {
    private val weatherForecastDetailDao = db.weatherForecastDetailDao()
    private val weatherDao = db.weatherDao()
    private val weatherForecastDao = db.weatherForecastDao()

    @SuppressLint("CheckResult")
    fun getWeatherForecastDetailsByWeatherForecast(
        weatherForecastId :Long,
        callback : WeatherForecastDetailViewModel.WeatherForecastDetailsCallBack
    ) {
       getWeatherForecastDetailsSingle(weatherForecastId)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeWith(object : DisposableSingleObserver<List<WeatherForecastDetail>>(){
               override fun onSuccess(t: List<WeatherForecastDetail>) {
                   callback.onDataReceived(t)
               }

               override fun onError(e: Throwable) {
                   callback.onError("Unknown error")
               }
           })


    }

    private fun getWeatherForecastDetailsSingle(weatherForecastId :Long) :Single<List<WeatherForecastDetail>> {
       return  Single.fromCallable {
            val wfdMappingList = weatherForecastDetailDao
                .getWFDsByWeatherForecastId(weatherForecastId)

            val wfdList = mutableListOf<WeatherForecastDetail>()
            for (wfdMapping in wfdMappingList) {
                val weather = weatherDao.getWeatherById(wfdMapping.weatherId)

                wfdList.add(WeatherForecastDetail(wfdMapping, weather, null))
            }

            return@fromCallable wfdList
        }
    }
}