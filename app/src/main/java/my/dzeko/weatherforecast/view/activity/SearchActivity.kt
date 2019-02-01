package my.dzeko.weatherforecast.view.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import my.dzeko.weatherforecast.R
import my.dzeko.weatherforecast.databinding.ActivitySearchBinding

const val CITY = "city"
class SearchActivity : AppCompatActivity() {

    private lateinit var mBinder :ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_search)

        mBinder.callback= object :CitySearchCallback{
            override fun searchCity(city: String) {
                val intent = Intent(this@SearchActivity, WeatherForecastActivity::class.java)
                intent.putExtra(CITY, city)
                startActivity(intent)
            }
        }

        mBinder.executePendingBindings()
    }

    interface CitySearchCallback{
        fun searchCity(city :String)

    }
}
