package com.example.weatherforecast.view.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.weatherforecast.model.WeatherDataModel
import com.example.weatherforecast.model.WeatherRepository
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.IllegalArgumentException

class WeatherHomeViewModel (private val weatherRepository: WeatherRepository):ViewModel() {
    /**
     * The data source this ViewModel will fetch results from.
     */

    fun getHome(lat: Double, lon: Double): LiveData<WeatherDataModel>{
        val liveWeatherData: LiveData<WeatherDataModel> = weatherRepository.getHome(lat,lon)
        return liveWeatherData
    }
     fun refreshDataFromRepository(context: Context) = viewModelScope.launch {
        try {
            Log.i("TAG", "refreshDataFromRepository: try ")
            weatherRepository.refreshWeatherData(context)
        } catch (networkError: IOException) {
            Log.i("TAG", "refreshDataFromRepository: catch")
        }

    }
    fun refreshHomeDataFromRepository(lat: Double, lon: Double) = viewModelScope.launch {
        try {
            Log.i("TAG", "refreshHomeDataFromRepository: try ")
            weatherRepository.insertWeatherData(lat,lon)
        } catch (networkError: IOException) {
            Log.i("TAG", "refreshHomeDataFromRepository: catch")
        }

    }
     fun deleteAlarmById(id:Int)=viewModelScope.launch {
        weatherRepository.deleteAlarmById(id)
    }

//
//    init {
//        refreshDataFromRepository()
//    }


}
class WeatherHomeViewModelFactory(private val weatherRepository: WeatherRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherHomeViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WeatherHomeViewModel(
                weatherRepository
            ) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel class")
    }

}
