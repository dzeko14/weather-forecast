package my.dzeko.weatherforecast.repository

import android.annotation.SuppressLint
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import my.dzeko.weatherforecast.entity.WeatherForecast
import my.dzeko.weatherforecast.manager.AppPreferencesManager
import my.dzeko.weatherforecast.manager.ConnectionManager
import my.dzeko.weatherforecast.viewmodel.WeatherForecastViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherForecastRepository @Inject constructor(
    private val mApiRepo :ApiWeatherForecastRepository,
    private val mPrefManager :AppPreferencesManager,
    private val mLocalRepo :LocalWeatherForecastRepository,
    private val mConnectionManager :ConnectionManager
) {

    @SuppressLint("CheckResult")
    fun getWeatherForecast(callback: WeatherForecastViewModel.WeatherForecastCallback){
        val location = mPrefManager.getMarkerLongLat()

        var isLocalRepo = false
        val repo = if(mConnectionManager.isConnectedToInternet) mApiRepo
        else{
            isLocalRepo = true
            mLocalRepo
        }

        //Getting Weather Forecast
        var singleWithData = repo.getWeatherForecastByLocation(location)

        if (!isLocalRepo){
             singleWithData = saveDataFromApi(singleWithData)
        }



        singleWithData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<WeatherForecast>>(){
                override fun onSuccess(t: List<WeatherForecast>) {
                    callback.onDataReceived(t)

                }

                override fun onError(e: Throwable) {
                    when(e) {
                        is HttpException ->
                            callback.onError("There is something with the server!" +
                                    " Please, try later!")
                        else -> callback.onError("Unknown error!")
                    }
                }
            })


    }

    @SuppressLint("CheckResult")
    private fun saveDataFromApi(weatherForecastsSingle : Single<List<WeatherForecast>>)
    : Single<List<WeatherForecast>>{
       return  mLocalRepo.saveWeatherForecasts(weatherForecastsSingle)
    }

    @SuppressLint("CheckResult")
    fun getWeatherForecastByCity(
        city: String,
        callback: WeatherForecastViewModel.WeatherForecastCallback
    ) {
        var isLocalRepo = false
        val repo = if(mConnectionManager.isConnectedToInternet) mApiRepo
        else{
            isLocalRepo = true
            mLocalRepo
        }

        //Getting Weather Forecast
        var singleWithData = repo.getWeatherForecastByCity(city)

        if (!isLocalRepo){
            singleWithData = saveDataFromApi(singleWithData)
        }

        singleWithData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<WeatherForecast>>(){
                override fun onSuccess(t: List<WeatherForecast>) {
                    callback.onDataReceived(t)

                }

                override fun onError(e: Throwable) {
                    when(e) {
                        is HttpException ->
                            callback.onError("Cant find such city or region")
                        else -> callback.onError("Unknown error!")
                    }
                }
            })
    }
}