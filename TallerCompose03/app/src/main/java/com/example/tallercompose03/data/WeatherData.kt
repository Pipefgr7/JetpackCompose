package com.example.tallercompose03.data

data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)

data class Main(
    val temp: Double,
    val humidity: Int,
    val feels_like: Double
)

data class Weather(
    val description: String,
    val icon: String
)

data class Wind(
    val speed: Double
)