package com.example.weatherforecast.view.home

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weatherforecast.Language
import com.example.weatherforecast.R
import com.example.weatherforecast.WeatherApplication
import com.example.weatherforecast.model.Daily
import com.example.weatherforecast.model.Hourly
import com.example.weatherforecast.model.WeatherDataModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*
class Home : Fragment() {
    val weatherHomeViewModel : WeatherHomeViewModel by viewModels {
        WeatherHomeViewModelFactory((requireActivity().application as WeatherApplication).repository)
    }

    var hourlyListAdapter =
        HourlyListAdapter(arrayListOf())
    var dailyListAdapter =
        DailyListAdapter(arrayListOf())

    lateinit var temp:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        val view:View= inflater.inflate(R.layout.fragment_home, container, false)

        weatherHomeViewModel.refreshDataFromRepository(requireContext())
        // Inflate the layout for this fragment
         val recyclerViewForHourly:RecyclerView=view.findViewById(R.id.home_hourly_recyclerview)
        recyclerViewForHourly.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerViewForHourly.adapter=hourlyListAdapter

        val recyclerViewForDaily:RecyclerView=view.findViewById(R.id.home_daily_recyclerview)
        recyclerViewForDaily.layoutManager=LinearLayoutManager(requireContext())
        recyclerViewForDaily.adapter=dailyListAdapter

        Log.i("TAG", "home: ")

        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val lat= sp.getString("lat","")
        val lon=sp.getString("lon","")
        val lan=sp.getString("language","")
        temp= sp.getString("Temperature","").toString()
//        if(lan=="Arabic")
//            Language.setLocale(requireActivity(),"ar")

        if (lat != "") {
            if (lon != "") {
                if (lat != null) {
                    if (lon != null) {
           //             weatherHomeViewModel.refreshHomeDataFromRepository(lat.toDouble(),lon.toDouble())
                    }
                }
            }
        }

        if (lat != "") {
            if (lon != "") {
                if (lat != null) {
                    if (lon != null) {

//                                val latcast = String.format("%.4f", lat.toDouble()).toDouble()
//                                val loncast = String.format("%.4f", lon.toDouble()).toDouble()
                                weatherHomeViewModel.getHome(round(lat.toDouble(),4), round(lon.toDouble(),4))
                                    .observe(viewLifecycleOwner, Observer { data ->
                                        data?.let {

                                            updaMainLayout(it)
                                            data.hourly?.let { it1 -> updateHourlyListUI(it1) }
                                            data.daily?.let { it1 -> updateDailyListUI(it1) }
                                            data.let { updateDetailsLayout(it) }

                                        }
                                    })

                            }

                }
            }
        }

       // initUI()
        return view
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
        if(temp=="Fahrenheit"){
            home_data_windSpeed.text= it.current?.windSpeed.toString()+"m/h"
        }else{
            home_data_windSpeed.text= it.current?.windSpeed.toString()+"m/s"
        }

        home_data_cloudCover.text=it.current?.clouds.toString()+"%"
        home_data_humidity.text=it.current?.humidity.toString()+"'%"
        home_data_visibility.text=it.current?.visibility.toString()
        home_data_pressure.text=it.current?.pressure.toString()+"mbar"
        home_data_precipitation.text= it.minutely?.get(0)?.precipitation.toString()+"mm"
    }
    private fun updaMainLayout(it: WeatherDataModel){
        home_location_name.text= it.timezone?.substringAfter("/")
        val calendar = Calendar.getInstance()
        val tz = TimeZone.getDefault()
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm a", Locale.getDefault())
        val currenTimeZone = (it.current?.dt?.toLong())?.times(1000)?.let { it1 -> Date(it1) }
        home_date_time.text= sdf.format(currenTimeZone)
        val options = RequestOptions()
            .error(R.mipmap.ic_launcher_round)
        val icon:String?= it.current?.weather?.get(0)?.icon
        this?.let { it1 ->
            Glide.with(it1)
                .setDefaultRequestOptions(options)
                .load(icon?.let { it2 -> getIcon(it2) })
                .into(home_icon_weather)
        }
        home_weather_desc.text= it.current?.weather?.get(0)?.description
        home_max.text= "max: "+(it.daily?.get(0)?.temp?.max)?.toInt().toString()+"\u00B0"
        home_min.text= "min: "+ (it.daily?.get(0)?.temp?.min)?.toInt().toString()+"\u00B0"
        home_data_precipitation.text= "prec"+(it.minutely?.get(0)?.precipitation)?.toInt().toString()+"%"
        home_data_windSpeed.text="wind"+(it.current?.windSpeed?.toInt()).toString()+"mph"
        home_data_cloudCover.text= "clods"+ it.current?.clouds.toString()+"%"

        if(temp=="Fahrenheit"){
            home_temp.text =(it.current?.temp)?.toInt().toString()+" "+ "\u2109"
        }else if(temp=="Celsius"){
            home_temp.text =(it.current?.temp)?.toInt().toString()+" "+  "\u2103"
        }else{
            home_temp.text =(it.current?.temp)?.toInt().toString()+" "+ "\u00B0"
        }
    }

    fun round(value: Double, places: Int): Double {
        var value = value
        require(places >= 0)
        val factor = Math.pow(10.0, places.toDouble()).toLong()
        value = value * factor
        val tmp = Math.round(value)
        return tmp.toDouble() / factor
    }


}
