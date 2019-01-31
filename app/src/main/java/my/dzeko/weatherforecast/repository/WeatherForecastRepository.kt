package my.dzeko.weatherforecast.repository

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import my.dzeko.weatherforecast.entity.WeatherForecast
import my.dzeko.weatherforecast.manager.AppPreferencesManager
import my.dzeko.weatherforecast.viewmodel.WeatherForecastViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherForecastRepository @Inject constructor(
    private val mApiRepo :ApiWeatherForecastRepository,
    private val mPrefManager :AppPreferencesManager
) {

    @SuppressLint("CheckResult")
    fun getWeatherForecast(callback: WeatherForecastViewModel.WeatherForecastCallback){
        val location = mPrefManager.getMarkerLongLat()

        mApiRepo.getWeatherForecastByLocation(location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<WeatherForecast>>(){
                override fun onSuccess(t: List<WeatherForecast>) {
                    callback.onDataReceived(t)
                }

                override fun onError(e: Throwable) {
                    //Temp
                    throw e
                }
            })
    }


}