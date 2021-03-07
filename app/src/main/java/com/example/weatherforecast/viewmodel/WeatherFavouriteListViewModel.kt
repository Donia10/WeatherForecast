package com.example.weatherforecast.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.roomwordsample.model.WeatherDatabase.Companion.getDatabase
import com.example.weatherforecast.model.WeatherDataModel
import com.example.weatherforecast.model.WeatherRepository
import com.example.weatherforecast.model.local.NameTuple
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.IllegalArgumentException

class WeatherFavouriteViewModel (private val weatherRepository: WeatherRepository):ViewModel() {
    /**
     * The data source this ViewModel will fetch results from.
     */
    val liveFavLocations: LiveData<List<WeatherDataModel>> = weatherRepository.weatherFavLocations

     fun refreshFavDataFromRepository(lat:Double,lon:Double) = viewModelScope.launch {
        try {
            Log.i("TAG", "refreshFavDataFromRepository: try ")
            weatherRepository.insertWeatherData(lat,lon)
        } catch (networkError: IOException) {
            Log.i("TAG", "refreshFavDataFromRepository: catch")
        }

    }

    /*init {
        refreshFavDataFromRepository()
    }*/

}
class WeatherFavouriteListViewModelFactory(private val weatherRepository: WeatherRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherFavouriteViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WeatherFavouriteViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel class")
    }

}
