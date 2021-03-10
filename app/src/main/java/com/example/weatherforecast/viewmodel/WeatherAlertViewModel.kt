package com.example.weatherforecast.viewmodel

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.SystemClock
import android.util.Property
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.*
import com.example.weatherforecast.AlarmReceiver
import com.example.weatherforecast.model.WeatherDataModel
import com.example.weatherforecast.model.WeatherRepository
import com.example.weatherforecast.model.local.AlarmData
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.*

class WeatherAlertViewModel (private val weatherRepository: WeatherRepository) :ViewModel() {

    val alarmData=weatherRepository.alarmData
     fun setAlarm(alarmData: AlarmData)=viewModelScope.launch {
        weatherRepository.setAlarm(alarmData)
    }
}
class WeatherAlertViewModelFactory(private val weatherRepository: WeatherRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherAlertViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WeatherAlertViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel class")
    }


}