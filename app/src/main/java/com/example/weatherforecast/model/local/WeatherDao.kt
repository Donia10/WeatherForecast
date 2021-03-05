
package com.example.weatherforecast.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherforecast.model.WeatherDataModel

@Dao
interface WeatherDao {
    //flow or live data to get livedata
    // Using Flow with Room allows you to get live updates. This means that every time there's a change in the user table, a new User will be emitted.
    @Query("select * from Weather WHERE lat=:lat and lon=:lon")
    fun getWeatherData(lat:Double,lon:Double):LiveData<WeatherDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherData( weatherData: WeatherDataModel)
}
