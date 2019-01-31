package my.dzeko.weatherforecast.entity


import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import my.dzeko.weatherforecast.entity.response.CityResponse

@Entity(indices = [
    Index(value = arrayOf("name"), unique = true)
])
data class City(
    @PrimaryKey var id : Long,
    val name :String,
    val country :String,
    val longitude :Double,
    val latitude :Double
    ) {
    constructor(cityResponse: CityResponse)
            :this(
        cityResponse.id,
        cityResponse.name,
        cityResponse.country,
        cityResponse.coordinate.lon,
        cityResponse.coordinate.lat
        )
}