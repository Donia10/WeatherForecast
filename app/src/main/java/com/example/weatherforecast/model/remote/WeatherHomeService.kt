package com.example.weatherforecast.model.remote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherHomeService {
        private const val BASE_URL = "https://api.openweathermap.org/"
        private const val Img_URL="http://openweathermap.org/"
        fun getWeatherService(): WeatherHomeApi {
            return Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(WeatherHomeApi::class.java)

        }
}