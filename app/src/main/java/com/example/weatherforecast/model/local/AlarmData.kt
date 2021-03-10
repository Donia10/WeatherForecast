package com.example.weatherforecast.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName ="Weather")
data class AlarmData (val alarmStatus:Boolean,val time:Long,val forNext:String,val alarmSound:String){
  //  @PrimaryKey(autoGenerate = false)
    val id=0
}