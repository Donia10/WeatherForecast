package com.example.weatherforecast.view.favouriteLoactions

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import com.example.weatherforecast.R
import com.example.weatherforecast.WeatherApplication
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weatherforecast.model.Daily
import com.example.weatherforecast.model.Hourly
import com.example.weatherforecast.model.WeatherDataModel
import kotlinx.android.synthetic.main.activity_weather_data_fav.*
import java.text.SimpleDateFormat
import java.util.*

class WeatherDataFav : AppCompatActivity() {
    var hourlyListAdapter =
        FavHourlyListAdapter(arrayListOf())
    var dailyListAdapter =
        FavDailyListAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_data_fav)
     //   updateMainInfoUI()
        val WeatherDataFavViewModel : WeatherDataFavViewModel by viewModels {
            WeatherDataFavViewModelFactory(
                (this.application as WeatherApplication).repository
            )
        }
        val toolBar =findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)
        fav_hourly_recyclerview.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        fav_hourly_recyclerview.adapter=hourlyListAdapter
        fav_daily_recyclerview.layoutManager=LinearLayoutManager(this)
        fav_daily_recyclerview.adapter=dailyListAdapter

        val intent:Intent=getIntent()
        val lat=intent.getDoubleExtra("latOfSelectedFavObject",0.0)
        val lon=intent.getDoubleExtra("lonOfSelectedFavObject",0.0)

        Toast.makeText(this,"$lat ,$lon",Toast.LENGTH_SHORT).show()
        WeatherDataFavViewModel.getFav(lat,lon).observe(this, Observer {
           updaMainLayout(it)
            it.hourly?.let { it1 -> updateHourlyListUI(it1) }
            it.daily?.let { it1 -> updateDailyListUI(it1) }
            updateDetailsLayout(it)
        })

    }

    private fun updaMainLayout(it: WeatherDataModel){
        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val temp= sp.getString("Temperature","").toString()
        fav_location_name.text= it.timezone?.substringAfter("/")
        val calendar = Calendar.getInstance()
        val tz = TimeZone.getDefault()
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm a", Locale.getDefault())
        val currenTimeZone = (it.current?.dt?.toLong())?.times(1000)?.let { it1 -> Date(it1) }
        fav_date_time.text= sdf.format(currenTimeZone)

        if(temp=="Fahrenheit"){
            fav_temp.text =(it.current?.temp)?.toInt().toString()+" "+ "\u2109"
        }else if(temp=="Celsius"){
            fav_temp.text =(it.current?.temp)?.toInt().toString()+" "+  "\u2103"
        }else{
            fav_temp.text =(it.current?.temp)?.toInt().toString()+" "+ "\u00B0"
        }
        val options = RequestOptions()
            .error(R.mipmap.ic_launcher_round)
        val icon:String?= it.current?.weather?.get(0)?.icon
        this?.let { it1 ->
            Glide.with(it1)
                .setDefaultRequestOptions(options)
                .load(icon?.let { it2 -> getIcon(it2) })
                .into(fav_icon_weather)
        }

        fav_weather_desc.text= it.current?.weather?.get(0)?.description
        fav_max.text= "max: "+(it.daily?.get(0)?.temp?.max)?.toInt().toString()
        fav_min.text= "min: "+ (it.daily?.get(0)?.temp?.min)?.toInt().toString()
//        precipitation.text= "prec"+(it.minutely?.get(0)?.precipitation)?.toInt().toString()+"%"
//        windSpeed.text="wind"+(it.current?.windSpeed?.toInt()).toString()+"mph"
//        clouds.text= "clods"+ it.current?.clouds.toString()+"%"

    }
    fun getIcon(icon: String): String {
        return "http://openweathermap.org/img/w/${icon}.png"
    }
    private fun updateHourlyListUI(it: List<Hourly>) {
        hourlyListAdapter.updateHoursList(it)
    }
    private fun updateDailyListUI(it: List<Daily>) {
        dailyListAdapter.updateHoursList(it)
    }
    private fun updateDetailsLayout(it:WeatherDataModel){
        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val temp= sp.getString("Temperature","").toString()
        if(temp=="Fahrenheit"){
            fav_data_windSpeed.text= it.current?.windSpeed.toString()+" "+ "miles/hour"
        }else{
            fav_data_windSpeed.text= it.current?.windSpeed.toString()+" "+ " meter/sec"
        }
        fav_data_cloudCover.text=it.current?.clouds.toString()+" %"
        fav_data_humidity.text=it.current?.humidity.toString()+" %"
        fav_data_visibility.text=it.current?.visibility.toString()
        fav_data_pressure.text=it.current?.pressure.toString()+" mbar"
        fav_data_precipitation.text= it.minutely?.get(0)?.precipitation.toString()+" mm"
    }


}