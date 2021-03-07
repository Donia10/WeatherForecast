package com.example.weatherforecast.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.roomwordsample.model.WeatherDatabase.Companion.getDatabase
import com.example.weatherforecast.model.WeatherDataModel
import com.example.weatherforecast.model.WeatherRepository
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.IllegalArgumentException

class WeatherHomeViewModel (private val weatherRepository: WeatherRepository):ViewModel() {
    /**
     * The data source this ViewModel will fetch results from.
     */
   // private val weatherRepository = WeatherRepository(getDatabase(getApplication()))
    val liveWeatherData: LiveData<WeatherDataModel> = weatherRepository.weatherLiveData

    private fun refreshDataFromRepository() = viewModelScope.launch {
        try {
            Log.i("TAG", "refreshDataFromRepository: try ")
            weatherRepository.refreshWeatherData()
        } catch (networkError: IOException) {
            Log.i("TAG", "refreshDataFromRepository: catch")
        }

    }

    init {
        refreshDataFromRepository()
    }

}
class WeatherHomeViewModelFactory(private val weatherRepository: WeatherRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherHomeViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WeatherHomeViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel class")
    }

}
