package com.example.weatherforecast.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.weatherforecast.model.WeatherRepository
import java.lang.IllegalArgumentException

class SettingViewModel( private val weatherRepository: WeatherRepository):ViewModel() {



}
class SettingViewModelFactory(private val weatherRepository: WeatherRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SettingViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SettingViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel class")
    }
}