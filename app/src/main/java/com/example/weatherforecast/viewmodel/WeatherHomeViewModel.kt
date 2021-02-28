package com.example.weatherforecast.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.model.WeatherDataModel
import com.example.weatherforecast.model.WeatherHomeService
import kotlinx.coroutines.*

class WeatherHomeViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<WeatherDataModel>()
    fun fetchData() {
        val exceptionHandlerException = CoroutineExceptionHandler { _, _ ->
            Log.i("TAG", "exce: ")
        }
        CoroutineScope(Dispatchers.IO /*+ exceptionHandlerException**/).launch {

            val response = WeatherHomeService.getWeatherService().getWeatherForecast("33.441792","-94.037689","c9f08d5ea2902a1721e39bea2c8ccac0","hourly,daily,minutely")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countryLiveData.postValue(response.body())
                    Log.i("TAG", "sucesss: ")

                }else{
                    Log.i("TAG", "fail: ")
                }
            }
        }

    }


}