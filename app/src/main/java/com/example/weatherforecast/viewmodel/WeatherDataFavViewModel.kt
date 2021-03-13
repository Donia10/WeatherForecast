package com.example.weatherforecast.viewmodel


import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.weatherforecast.model.WeatherDataModel
import com.example.weatherforecast.model.WeatherRepository
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.IllegalArgumentException

class WeatherDataFavViewModel (private val weatherRepository: WeatherRepository):ViewModel() {
    /**
     * The data source this ViewModel will fetch results from.
     */

    fun getFav(lat: Double, lon: Double): LiveData<WeatherDataModel>{
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
    fun refreshFavDataFromRepository(lat: Double, lon: Double) = viewModelScope.launch {
        try {
            Log.i("TAG", "refreshFavDataFromRepository: try ")
            weatherRepository.insertWeatherData(lat,lon)
        } catch (networkError: IOException) {
            Log.i("TAG", "refreshFavDataFromRepository: catch")
        }

    }

}
class WeatherDataFavViewModelFactory(private val weatherRepository: WeatherRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherDataFavViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WeatherDataFavViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel class")
    }

}
