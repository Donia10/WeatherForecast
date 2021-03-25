package com.example.roomwordsample.model

import android.content.Context
import androidx.room.*
import com.example.weatherforecast.model.local.DataConverter
import com.example.weatherforecast.model.local.WeatherDao
import com.example.weatherforecast.model.WeatherDataModel
import com.example.weatherforecast.model.local.AlarmData

@Database(entities = [WeatherDataModel::class , AlarmData::class],version = 1,exportSchema = false)
@TypeConverters(DataConverter::class)
 abstract class WeatherDatabase :RoomDatabase(){
    abstract fun weatherDao(): WeatherDao

    companion object {
        //singleton
        @Volatile
        private var INSTANCE: WeatherDatabase? = null
        fun getDatabase(context: Context): WeatherDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext, WeatherDatabase::class.java
                        , "word_database"
                    )
                        .build()
                    INSTANCE = instance
                    //return instance
                    return instance
                }
        }
    }
}