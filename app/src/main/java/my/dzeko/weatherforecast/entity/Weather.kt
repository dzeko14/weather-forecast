package my.dzeko.weatherforecast.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Weather(
    @PrimaryKey
    @Expose
    val id : Long,

    @Expose
    @SerializedName("main")
    val name :String,

    @Expose
    val description :String
)