package com.example.weatherforecast.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.model.Temp
import com.example.weatherforecast.model.FeelsLike
import com.example.weatherforecast.model.Weather__
import com.example.weatherforecast.model.Weather_
import com.example.weatherforecast.model.Rain
import com.example.weatherforecast.model.Minutely
import com.example.weatherforecast.model.Hourly
import com.example.weatherforecast.model.Daily

data class Current ( @SerializedName("dt") @Expose var dt: Int? ,
                     @SerializedName("sunrise") @Expose var sunrise: Int? ,
                     @SerializedName("sunset") @Expose var sunset: Int?,
                     @SerializedName("temp") @Expose var temp: Double?,
                     @SerializedName("feels_like") @Expose var feelsLike: Double? ,
                     @SerializedName("pressure") @Expose var pressure: Int?,

                        @SerializedName("humidity") @Expose var humidity: Int? ,
                        @SerializedName("dew_point") @Expose var dewPoint: Double? ,

                        @SerializedName("uvi") @Expose var uvi: Double? ,
                        @SerializedName("clouds") @Expose var clouds: Int? ,
                        @SerializedName("visibility") @Expose var visibility: Int?,

                        @SerializedName("wind_speed") @Expose var windSpeed: Double?,

                        @SerializedName("wind_deg") @Expose var windDeg: Int?,

                        @SerializedName("wind_gust") @Expose var windGust: Double?,

                        @SerializedName("weather") @Expose var weather: List<Weather>?)
{
    /*
    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null

    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null

    @SerializedName("temp")
    @Expose
    var temp: Double? = null

    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double? = null

    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null

    @SerializedName("dew_point")
    @Expose
    var dewPoint: Double? = null

    @SerializedName("uvi")
    @Expose
    var uvi: Double? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Int? = null

    @SerializedName("visibility")
    @Expose
    var visibility: Int? = null

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double? = null

    @SerializedName("wind_deg")
    @Expose
    var windDeg: Int? = null

    @SerializedName("wind_gust")
    @Expose
    var windGust: Double? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null
**/
}

data class Daily ( @SerializedName("dt") @Expose var dt: Int?,
                   @SerializedName("sunrise") @Expose var sunrise: Int? ,
                   @SerializedName("sunset") @Expose var sunset: Int? ,
                   @SerializedName("temp") @Expose var temp: Temp? ,
                   @SerializedName("feels_like") @Expose var feelsLike: FeelsLike? ,
                   @SerializedName("pressure") @Expose var pressure: Int? ,
                   @SerializedName("humidity") @Expose var humidity: Int? ,
                   @SerializedName("dew_point") @Expose var dewPoint: Double?,
                   @SerializedName("wind_speed") @Expose var windSpeed: Double?,
                   @SerializedName("wind_deg") @Expose var windDeg: Int? ,
                   @SerializedName("weather") @Expose var weather: List<Weather__>? ,
                   @SerializedName("clouds") @Expose var clouds: Int? ,
                   @SerializedName("pop") @Expose var pop: Int? ,
                   @SerializedName("rain") @Expose var rain: Double?,
                   @SerializedName("uvi") @Expose var uvi: Int?)
{
    /*
    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null

    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null

    @SerializedName("temp")
    @Expose
    var temp: Temp? = null

    @SerializedName("feels_like")
    @Expose
    var feelsLike: FeelsLike? = null

    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null

    @SerializedName("dew_point")
    @Expose
    var dewPoint: Double? = null

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double? = null

    @SerializedName("wind_deg")
    @Expose
    var windDeg: Int? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather__>? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Int? = null

    @SerializedName("pop")
    @Expose
    var pop: Int? = null

    @SerializedName("rain")
    @Expose
    var rain: Double? = null

    @SerializedName("uvi")
    @Expose
    var uvi: Int? = null
**/
}

data class FeelsLike (  @SerializedName("day") @Expose var day: Double?,
                        @SerializedName("night") @Expose var night: Double?,
                        @SerializedName("eve") @Expose var eve: Double?,
                        @SerializedName("morn") @Expose var morn: Double?)
{
    /*
    @SerializedName("day")
    @Expose
    var day: Double? = null

    @SerializedName("night")
    @Expose
    var night: Double? = null

    @SerializedName("eve")
    @Expose
    var eve: Double? = null

    @SerializedName("morn")
    @Expose
    var morn: Double? = null
**/
}

data class Hourly(   @SerializedName("dt") @Expose var dt: Int?,
                     @SerializedName("temp") @Expose var temp: Double?,
                     @SerializedName("feels_like") @Expose var feelsLike: Double? ,
                     @SerializedName("pressure") @Expose var pressure: Int? ,
                     @SerializedName("humidity") @Expose var humidity: Int?,
                     @SerializedName("dew_point") @Expose var dewPoint: Double? ,
                     @SerializedName("uvi") @Expose var uvi: Double?,
                     @SerializedName("clouds") @Expose var clouds: Int? ,
                     @SerializedName("visibility") @Expose var visibility: Int? ,
                     @SerializedName("wind_speed") @Expose var windSpeed: Double?,
                     @SerializedName("wind_deg") @Expose var windDeg: Int?,
                     @SerializedName("weather") @Expose var weather: List<Weather_>? ,
                     @SerializedName("pop") @Expose var pop: Int?,
                     @SerializedName("rain") @Expose var rain: Rain?)
{
    /*
    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("temp")
    @Expose
    var temp: Double? = null

    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double? = null

    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null

    @SerializedName("dew_point")
    @Expose
    var dewPoint: Double? = null

    @SerializedName("uvi")
    @Expose
    var uvi: Double? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Int? = null

    @SerializedName("visibility")
    @Expose
    var visibility: Int? = null

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double? = null

    @SerializedName("wind_deg")
    @Expose
    var windDeg: Int? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather_>? = null

    @SerializedName("pop")
    @Expose
    var pop: Int? = null

    @SerializedName("rain")
    @Expose
    var rain: Rain? = null
**/
}

data class Minutely (    @SerializedName("dt") @Expose var dt: Int?,
                         @SerializedName("precipitation") @Expose var precipitation: Int? )
{
    /*
    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("precipitation")
    @Expose
    var precipitation: Int? = null
**/
}

class Rain {

    @SerializedName("1h")
    @Expose
    private var _1h: Double? = null
    fun get1h(): Double? {
        return _1h
    }

    fun set1h(_1h: Double?) {
        this._1h = _1h
    }
}

data class Temp(  @SerializedName("day") @Expose var day: Double? ,
                  @SerializedName("min") @Expose var min: Double?,
                  @SerializedName("max") @Expose var max: Double? ,
                  @SerializedName("night") @Expose var night: Double? ,
                  @SerializedName("eve") @Expose var eve: Double? ,
                  @SerializedName("morn") @Expose var morn: Double?)
{
    /*
    @SerializedName("day")
    @Expose
    var day: Double? = null

    @SerializedName("min")
    @Expose
    var min: Double? = null

    @SerializedName("max")
    @Expose
    var max: Double? = null

    @SerializedName("night")
    @Expose
    var night: Double? = null

    @SerializedName("eve")
    @Expose
    var eve: Double? = null

    @SerializedName("morn")
    @Expose
    var morn: Double? = null
**/
}

data class Weather (    @SerializedName("id") @Expose var id: Int? ,
                        @SerializedName("main") @Expose var main: String? ,
                        @SerializedName("description") @Expose var description: String? ,
                        @SerializedName("icon") @Expose var icon: String?){
    /*
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("main")
    @Expose
    var main: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null
**/
}

data class WeatherDataModel( @SerializedName("lat")
                             @Expose var lat: Double?,@SerializedName("lon")
                                @Expose var lon: Double? ,@SerializedName("timezone")
                                @Expose var timezone: String? ,@SerializedName("timezone_offset")
                                @Expose var timezoneOffset: Int? ,
                                @SerializedName("current")
                                @Expose var current: Current? ,@SerializedName("minutely")
                             @Expose var minutely: List<Minutely>? ,@SerializedName("hourly")
                             @Expose var hourly: List<Hourly>? , @SerializedName("daily")
                             @Expose var daily: List<Daily>?) {
    /*
    @SerializedName("lat")
    @Expose
    var lat: Double? = null

    @SerializedName("lon")
    @Expose
    var lon: Double? = null

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null

    @SerializedName("timezone_offset")
    @Expose
    var timezoneOffset: Int? = null

    @SerializedName("current")
    @Expose
    var current: Current? = null

    @SerializedName("minutely")
    @Expose
    var minutely: List<Minutely>? = null

    @SerializedName("hourly")
    @Expose
    var hourly: List<Hourly>? = null

    @SerializedName("daily")
    @Expose
    var daily: List<Daily>? = null
**/

}

class Weather_ {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("main")
    @Expose
    var main: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

}

class Weather__ {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("main")
    @Expose
    var main: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

}