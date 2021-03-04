package com.example.roomwordsample.model

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.weatherforecast.model.DataConverter
import com.example.weatherforecast.model.WeatherDao
import com.example.weatherforecast.model.WeatherDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [WeatherDataModel::class],version = 1,exportSchema = false)
@TypeConverters(DataConverter::class)
public abstract class WeatherDatabase :RoomDatabase(){
    abstract fun weatherDao(): WeatherDao

    companion object{
        //singleton
        @Volatile
        private var INSTANCE : WeatherDatabase?=null
        fun getDatabase(context :Context): WeatherDatabase {
            return INSTANCE
                ?: synchronized(this){
                    val instance =Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java
                        ,"weatherDatabase")
                        .build()
                    INSTANCE =instance
                    //return instance
                    return instance
                }

        }

    }


}