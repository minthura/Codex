package com.minthuya.sample.data.dtos

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Weather(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: Wind
)

@Keep
data class Clouds(
    val all: Int
)

@Keep
data class Coord(
    val lat: Double,
    val lon: Double
)

@Keep
data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)

@Keep
data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)

@Keep
data class WeatherX(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

@Keep
data class Wind(
    val deg: Int,
    val speed: Double
)