package com.example.weatherforecast.view.weatherAlerts

import androidx.lifecycle.*
import com.example.weatherforecast.model.WeatherRepository
import com.example.weatherforecast.model.local.AlarmData
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WeatherAlertViewModel (private val weatherRepository: WeatherRepository) :ViewModel() {

    val alarmData :LiveData<MutableList<AlarmData>> = weatherRepository.alarmData
     fun setAlarm(alarmData: AlarmData):Long {
         var id=-1L
         viewModelScope.launch {
             id=weatherRepository.setAlarm(alarmData)
         }
         return id

     }
    fun deleteAlarm(alarmData: AlarmData)=viewModelScope.launch {
        weatherRepository.deleteAlarm(alarmData)
    }
}
class WeatherAlertViewModelFactory(private val weatherRepository: WeatherRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherAlertViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WeatherAlertViewModel(
                weatherRepository
            ) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel class")
    }


}