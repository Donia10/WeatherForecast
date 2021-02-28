package com.example.weatherforecast.model
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherHomeService {
        private const val BASE_URL = "https://api.openweathermap.org/"
        fun getWeatherService(): WeatherHomeApi {
            return Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(WeatherHomeApi::class.java)
        }
}