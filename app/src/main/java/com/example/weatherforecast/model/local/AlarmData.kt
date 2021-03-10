package com.example.weatherforecast.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="AlarmData")
data class AlarmData (val alarmStatus:Boolean,val time:Long,val forNext:String,val alarmSound:String,val event:String){
  @PrimaryKey(autoGenerate = true)
    var id:Int=0
}