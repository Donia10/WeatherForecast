package com.example.weatherforecast

import android.app.Application
import android.content.Intent
import com.example.roomwordsample.model.WeatherDatabase
import com.example.weatherforecast.model.WeatherRepository
import java.util.Locale;
import android.app.Activity;
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate


class WeatherApplication:Application() {
     val database by lazy {  WeatherDatabase.getDatabase(this)}
    val repository by lazy {
        WeatherRepository(
            database.weatherDao()
        )
    }

}