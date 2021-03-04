package com.example.weatherforecast.model

import android.app.Application
import com.example.roomwordsample.model.WeatherDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WeatherApplication:Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy {  WeatherDatabase.getDatabase(this)}
    val repository by lazy { WeatherRepository(database) }
}