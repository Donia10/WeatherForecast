package com.example.weatherforecast.model.remote

import com.example.weatherforecast.model.WeatherDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherHomeApi {
    @GET("data/2.5/onecall?")
    suspend fun getWeatherForecast(@Query("lat") lat:String?,
                                   @Query("lon") lon: String?,
                                   @Query("appid") appid:String,
                                   @Query("units") units:String,
                                   @Query("lan") lan:String
        //       @Query("exclude")exclude:String
                                   ): Response<WeatherDataModel>

}