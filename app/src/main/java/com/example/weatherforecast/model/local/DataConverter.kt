
package com.example.weatherforecast.model.local

import androidx.room.TypeConverter
import com.example.weatherforecast.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun fromMinutelylist(minutelyList: List<Minutely>?): String? {
        if ( minutelyList== null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Minutely>>() {}.type
        return gson.toJson(minutelyList, type)
    }

    @TypeConverter
    fun toMinutelylist(minutelylist: String?): List<Minutely>? {
        if (minutelylist == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<List<Minutely>>() {}.type
        return gson.fromJson<List<Minutely>>(minutelylist, type)
    }

    @TypeConverter
    fun fromHourlyList(hourlyList: List<Hourly>?): String? {
        if (hourlyList == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Hourly>>() {}.type
        return gson.toJson(hourlyList, type)
    }

    @TypeConverter
    fun toHourlyList(hourlyList: String?): List<Hourly>? {
        if (hourlyList == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<List<Hourly>>() {}.type
        return gson.fromJson<List<Hourly>>(hourlyList, type)
    }

    @TypeConverter
    fun fromDailyList(dailyList: List<Daily>?): String? {
        if (dailyList == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Daily>>() {}.type
        return gson.toJson(dailyList, type)
    }

    @TypeConverter
    fun toDailyList(dailyList: String?): List<Daily>? {
        if (dailyList == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<List<Daily>>() {}.type
        return gson.fromJson<List<Daily>>(dailyList, type)
    }

    @TypeConverter
    fun fromWeatherList(weatherList: List<Weather>?): String? {
        if (weatherList == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Weather>>() {}.type
        return gson.toJson(weatherList, type)
    }

    @TypeConverter
    fun toWeatherList(weatherList: String?): List<Weather>?{
        if (weatherList == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson<List<Weather>>(weatherList, type)
    }

    @TypeConverter
    fun fromWeather_List(weatherList: List<Weather_>?): String? {
        if (weatherList == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Weather_>>() {}.type
        return gson.toJson(weatherList, type)
    }

    @TypeConverter
    fun toWeather_List(weatherList: String?): List<Weather_>? {
        if (weatherList == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<List<Weather_>>() {}.type
        return gson.fromJson<List<Weather_>>(weatherList, type)
    }

    @TypeConverter
    fun fromWeather__List(weatherList: List<Weather__>?): String? {
        if (weatherList == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Weather__>>() {}.type
        return gson.toJson(weatherList, type)
    }

    @TypeConverter
    fun toWeather__List(weatherList: String?): List<Weather__>? {
        if (weatherList == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<List<Weather__>>() {}.type
        return gson.fromJson<List<Weather__>>(weatherList, type)
    }
    @TypeConverter
    fun fromFeelLike(feelsLike: FeelsLike): String? {
        if (feelsLike == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<FeelsLike>() {}.type
        return gson.toJson(feelsLike, type)
    }

    @TypeConverter
    fun toFeelLike(feelsLike: String?): FeelsLike? {
        if (feelsLike == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<FeelsLike>() {}.type
        return gson.fromJson<FeelsLike>(feelsLike, type)
    }
    @TypeConverter
    fun fromRain(rain: Rain): String? {
        if (rain == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<Rain>() {}.type
        return gson.toJson(rain, type)
    }

    @TypeConverter
    fun toRain(rain: String?): Rain? {
        if (rain == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<Rain>() {}.type
        return gson.fromJson<Rain>(rain, type)
    }
    @TypeConverter
    fun fromCurrent(current: Current): String? {
        if (current == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<Current>() {}.type
        return gson.toJson(current, type)
    }
    @TypeConverter
    fun toCurrent(current: String? ): Current? {
        if (current == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<Current>() {}.type
        return gson.fromJson<Current>(current, type)
    }
    @TypeConverter
    fun fromHourly(hourly: Hourly): String? {
        if (hourly == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<Hourly>() {}.type
        return gson.toJson(hourly, type)
    }

    @TypeConverter
    fun toHourly(hourly: String?): Hourly? {
        if (hourly == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<Hourly>() {}.type
        return gson.fromJson<Hourly>(hourly, type)
    }
    @TypeConverter
    fun fromminutely(minutely: Minutely): String? {
        if (minutely == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<Minutely>() {}.type
        return gson.toJson(minutely, type)
    }

    @TypeConverter
    fun toMinutely(minutely: String?): Minutely? {
        if (minutely == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<Minutely>() {}.type
        return gson.fromJson<Minutely>(minutely, type)
    }
    @TypeConverter
    fun fromDaily(daily: Daily): String? {
        if (daily == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<Daily>() {}.type
        return gson.toJson(daily, type)
    }

    @TypeConverter
    fun toDaily(daily: String?): Daily? {
        if (daily == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<Daily>() {}.type
        return gson.fromJson<Daily>(daily, type)
    }
    @TypeConverter
    fun fromtemp(temp: Temp): String? {
        if (temp == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<Temp>() {}.type
        return gson.toJson(temp, type)
    }

    @TypeConverter
    fun toTemp(temp: String?): Temp? {
        if (temp == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<Temp>() {}.type
        return gson.fromJson<Temp>(temp, type)
    }
    @TypeConverter
    fun fromAlert(alert: Alert ?): String? {
        if (alert == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<Alert>() {}.type
        return gson.toJson(alert, type)
    }

    @TypeConverter
    fun toAlert(alert: String?): Alert? {
        if (alert == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<Alert>() {}.type
        return gson.fromJson<Alert>(alert, type)
    }
    @TypeConverter
    fun fromAlertList(alertList: List<Alert>?): String? {
        if (alertList == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Alert>>() {}.type
        return gson.toJson(alertList, type)
    }

    @TypeConverter
    fun toAlertList(alertList: String?): List<Alert>?{
        if (alertList == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<List<Alert>>() {}.type
        return gson.fromJson<List<Alert>>(alertList, type)
    }


    @TypeConverter
    fun fromWeather(weather: Weather?): String? {
        if (weather == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<Weather>() {}.type
        return gson.toJson(weather,type)
    }

    @TypeConverter
    fun toWeather(weather: String?): Weather?{
        if (weather == null) return null
        val gson = Gson()
        val type =
            object : TypeToken<Weather>() {}.type
        return gson.fromJson<Weather>(weather, type)
    }

}