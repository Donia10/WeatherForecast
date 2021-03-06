
package com.example.weatherforecast.model.local

import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.model.WeatherDataModel

@Dao
interface WeatherDao {
    //flow or live data to get livedata
    // Using Flow with Room allows you to get live updates. This means that every time there's a change in the user table, a new User will be emitted.
    @Query("select * from Weather ")
    fun getWeatherData():LiveData<WeatherDataModel>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherData( weatherData: WeatherDataModel)
}
