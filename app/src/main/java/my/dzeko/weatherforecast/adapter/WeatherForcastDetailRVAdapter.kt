package my.dzeko.weatherforecast.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import my.dzeko.weatherforecast.R
import my.dzeko.weatherforecast.databinding.WeatherForecastDetailRvItemBinding
import my.dzeko.weatherforecast.entity.WeatherForecastDetail

class WeatherForcastDetailRVAdapter (
    private var list : List<WeatherForecastDetail>
) :RecyclerView.Adapter<WeatherForcastDetailRVAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): VH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            .inflate<WeatherForecastDetailRvItemBinding>(layoutInflater,
                R.layout.weather_forecast_detail_rv_item,
                parent,
                false)
        return VH(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: VH, position: Int)
        = viewHolder.bind(list[position])

    fun updateData(list : List<WeatherForecastDetail>) {
        this.list = list
        notifyDataSetChanged()
    }

    class VH(private val binder :WeatherForecastDetailRvItemBinding) : RecyclerView.ViewHolder(binder.root){
        fun bind(item :WeatherForecastDetail) {
            binder.detailForecast = item
            binder.executePendingBindings()
        }
    }
}