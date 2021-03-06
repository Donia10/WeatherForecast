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

class WeatherFavouriteListViewModel (application: Application) :AndroidViewModel(application) {
    /**
     * The data source this ViewModel will fetch results from.
     */
    private val weatherRepository = WeatherRepository(getDatabase(application))
     val liveWeatherFavouriteList: LiveData<List<WeatherDataModel>> = weatherRepository.weatherLiveListFav


    fun refreshDataFromRepository() = viewModelScope.launch {
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
    fun requestWeatherData(lat:Double,lon:Double) = viewModelScope.launch {
        try {
            Log.i("TAG", "Request data with lalong: try ")
            weatherRepository.requestWeatherData(lat,lon)
        } catch (networkError: IOException) {
            Log.i("TAG", "Request data with lalong: catch")
        }

    }


    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class WeatherFavouriteListViewModelFactory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherFavouriteListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeatherFavouriteListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}