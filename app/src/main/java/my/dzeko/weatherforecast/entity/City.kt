package my.dzeko.weatherforecast.entity

import my.dzeko.weatherforecast.entity.response.CityResponse

data class City(
    val id : Long,
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