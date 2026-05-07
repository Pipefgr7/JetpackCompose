package com.example.tallercompose03.data

import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository(private val ciudadDao: CiudadDao) {

    private val api = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    suspend fun getWeather(city: String): WeatherResponse {
        val resultado = api.getWeather(city = city, apiKey = "ae0e1514db663baa35296842ec998602")
        ciudadDao.insertar(CiudadEntity(nombre = city)) // guarda en historial
        return resultado
    }

    fun getHistorial(): Flow<List<CiudadEntity>> = ciudadDao.getUltimas5()
}
