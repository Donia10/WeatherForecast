package com.example.weatherforecast.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.roomwordsample.model.WeatherDatabase
import com.example.weatherforecast.model.local.AlarmData
import com.example.weatherforecast.model.local.NameTuple
import com.example.weatherforecast.model.local.WeatherDao
import com.example.weatherforecast.model.remote.WeatherHomeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class WeatherRepository (private val weatherDao: WeatherDao) {

    val weatherLiveData:LiveData<WeatherDataModel> = weatherDao.getWeatherData(33.4418,-94.0377)

    val weatherFavLocations:LiveData<List<WeatherDataModel>> = weatherDao.getFavLocations()
    suspend fun refreshWeatherData(){
        //   val datarefresd = weatherDatabase.weatherDao().getHasRefreshed()
            withContext(Dispatchers.IO) {
                Log.i("TAG", "refresh weather is called and get data from api")
                val responseWeatherData = WeatherHomeService.getWeatherService().getWeatherForecast(
                    "33.441792",
                    "-94.037689",
                    "c9f08d5ea2902a1721e39bea2c8ccac0",
                    "metric"
                )
                responseWeatherData.body()
                    ?.let { weatherDao.insertWeatherData(it) }

            }
    }
    suspend fun getAlert():Alert{
       return weatherDao.getAlert(33.4418,-94.0377)
    }
    suspend fun insertWeatherData(lat:Double,lon:Double){
        //   val datarefresd = weatherDatabase.weatherDao().getHasRefreshed()
        withContext(Dispatchers.IO) {
            Log.i("TAG", "insertWeatherData is called and get data from api")
            val responseWeatherData = WeatherHomeService.getWeatherService().getWeatherForecast(
                "$lat",
                "$lon",
                "c9f08d5ea2902a1721e39bea2c8ccac0",
                "metric"
            )
            responseWeatherData.body()
                ?.let { weatherDao.insertWeatherData(it) }

        }
    }



    companion object {
        val FRESH_TIMEOUT = TimeUnit.MINUTES
    }
}