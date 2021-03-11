package com.example.weatherforecast.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager
import com.example.weatherforecast.model.local.AlarmData
import com.example.weatherforecast.model.local.WeatherDao
import com.example.weatherforecast.model.remote.WeatherHomeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository (private val weatherDao: WeatherDao) {


      fun  getHome(lat:Double,lon:Double):LiveData<WeatherDataModel>{
        val weatherLiveData:LiveData<WeatherDataModel> = weatherDao.getWeatherData(lat,lon)
          return weatherLiveData
    }

    var unit:String?=null
    var lat:Double?=null
    var lan:String?=null
    var lon:Double?=null
    val weatherFavLocations:LiveData<List<WeatherDataModel>> = weatherDao.getFavLocations()
    suspend fun refreshWeatherData(context: Context){
        //   val datarefresd = weatherDatabase.weatherDao().getHasRefreshed()
            getSp(context)
            withContext(Dispatchers.IO) {
                Log.i("TAG", "refresh weather is called and get data from api")
                if(unit!=null && lan!=null && lat!=null && lon!=null) {
                    val responseWeatherData =
                        WeatherHomeService.getWeatherService().getWeatherForecast(
                            lat,
                            lon,
                            "c9f08d5ea2902a1721e39bea2c8ccac0",
                            "$unit"
                        )
                    responseWeatherData.body()
                        ?.let { weatherDao.insertWeatherData(it) }
                }
            }
    }
    suspend fun getAlert():WeatherDataModel ?{
       return weatherDao.getAlert(33.4418,-94.0377)
    }
    suspend fun insertWeatherData(lat: Double?, lon: Double?){
        //   val datarefresd = weatherDatabase.weatherDao().getHasRefreshed()
        withContext(Dispatchers.IO) {
            Log.i("TAG", "insertWeatherData is called and get data from api")
          //  if(unit!=null && lan!=null) {
                val responseWeatherData = WeatherHomeService.getWeatherService().getWeatherForecast(
                    lat,
                    lon,
                    "c9f08d5ea2902a1721e39bea2c8ccac0",
                    "$unit"
                )
                responseWeatherData.body()
                    ?.let { weatherDao.insertWeatherData(it) }
            }
    //    }
    }
    suspend fun insertWeatherData(lat:Double, lon: Double){
        //   val datarefresd = weatherDatabase.weatherDao().getHasRefreshed()
        withContext(Dispatchers.IO) {
            Log.i("TAG", "insertWeatherData is called and get data from api")
            //  if(unit!=null && lan!=null) {
            val responseWeatherData = WeatherHomeService.getWeatherService().getWeatherForecast(
                lat,
                lon,
                "c9f08d5ea2902a1721e39bea2c8ccac0",
                "$unit"
            )
            responseWeatherData.body()
                ?.let { weatherDao.insertWeatherData(it) }
        }
        //    }
    }
    val alarmData:LiveData<MutableList<AlarmData>> = weatherDao.getAlarm()
    val alarm=weatherDao.getAlarm()
    suspend fun setAlarm(alarmData: AlarmData){
        withContext(Dispatchers.IO){
            weatherDao.setAlarm(alarmData)
        }
    }

    fun getSp(context: Context){
        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        if( sp.getString("Temperature","notfound")=="Fahrenheit"&&sp.getString("WindSpeed","no")=="miles/hour"){
            unit="imperial"

        }else  if( sp.getString("Temperature","notfound")=="Celsius"&&sp.getString("WindSpeed","no")=="meter/sec"){

            unit="metric"
        }else
        {

            unit="standard"
        }

          if(sp.getString("language","not")=="Arabic")
              lan="ar"
        else
           lat=sp.getFloat("lat",2f).toDouble()
           lon=sp.getFloat("lon",2f).toDouble()

    }

    suspend fun deleteAlarm(alarmData: AlarmData){
        withContext(Dispatchers.IO){
            weatherDao.deleteAlert(alarmData)
        }
    }

}