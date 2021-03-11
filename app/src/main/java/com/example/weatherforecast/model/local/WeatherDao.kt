
package com.example.weatherforecast.model.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.weatherforecast.model.Alert
import com.example.weatherforecast.model.WeatherDataModel

@Dao
interface WeatherDao {
    //flow or live data to get livedata
    // Using Flow with Room allows you to get live updates. This means that every time there's a change in the user table, a new User will be emitted.
    @Query("select * from Weather where lat=:lat and lon=:lon ")
    fun getWeatherData(lat:Double,lon:Double):LiveData<WeatherDataModel>
    @Query("select * from Weather")
    fun getFavLocations():LiveData<List<WeatherDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherData( weatherData: WeatherDataModel)
    @Delete
    fun deleteWeatherData(weatherData: WeatherDataModel)

    @Query("select * from Weather where lat=:lat and lon=:lon")
    fun getAlert(lat: Double,lon: Double):WeatherDataModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setAlarm(alarmData: AlarmData)
    @Query("select * from AlarmData")
    fun getAlarm():LiveData<MutableList<AlarmData>>
    @Delete
    fun deleteAlert(alarmData: AlarmData)
}
