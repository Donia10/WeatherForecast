package com.example.weatherforecast

import android.app.Application
import com.example.roomwordsample.model.WeatherDatabase
import com.example.weatherforecast.model.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WeatherApplication:Application() {
    private val database by lazy {  WeatherDatabase.getDatabase(this)}
    val repository by lazy {
        WeatherRepository(
            database.weatherDao()
        )
    }
}