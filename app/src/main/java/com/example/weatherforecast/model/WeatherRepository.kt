package com.example.weatherforecast.model

import android.util.Log
import com.example.roomwordsample.model.WeatherDatabase
import com.example.weatherforecast.model.remote.WeatherHomeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class WeatherRepository (val weatherDatabase: WeatherDatabase) {
    val weatherLiveData=weatherDatabase.weatherDao().getWeatherData()
    suspend fun refreshWeatherData(){
        //   val datarefresd = weatherDatabase.weatherDao().getHasRefreshed()
        if (true) {
            withContext(Dispatchers.IO) {
                Log.i("TAG", "refresh weather is called and get data from api")
                val responseWeatherData = WeatherHomeService.getWeatherService().getWeatherForecast(
                    "33.441792",
                    "-94.037689",
                    "c9f08d5ea2902a1721e39bea2c8ccac0",
                    "metric"
                )
                responseWeatherData.body()
                    ?.let { weatherDatabase.weatherDao().insertWeatherData(it) }

            }
        }
    }
    companion object {
        val FRESH_TIMEOUT = TimeUnit.MINUTES
    }
}