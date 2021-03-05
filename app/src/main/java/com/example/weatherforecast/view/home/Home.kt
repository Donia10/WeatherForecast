package com.example.weatherforecast.view.home

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weatherforecast.R
import com.example.weatherforecast.model.Daily
import com.example.weatherforecast.model.Hourly
import com.example.weatherforecast.model.WeatherDataModel
import com.example.weatherforecast.view.DailyListAdapter
import com.example.weatherforecast.view.HourlyListAdapter
import com.example.weatherforecast.viewmodel.WeatherHomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

class Home : Fragment()  {

    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onActivityCreated, which we
     * do in this Fragment.
     */
    private val viewModel: WeatherHomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, WeatherHomeViewModel.Factory(activity.application))
            .get(WeatherHomeViewModel::class.java)
    }
    var hourlyListAdapter =
        HourlyListAdapter(arrayListOf())
    var dailyListAdapter =
        DailyListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        // Inflate the layout for this fragment
       val view:View= inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerViewForHourly:RecyclerView=view.findViewById(R.id.recycler_view_hourly)
        recyclerViewForHourly.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerViewForHourly.adapter=hourlyListAdapter

        val recyclerViewForDaily:RecyclerView=view.findViewById(R.id.recycler_view_daily)
        recyclerViewForDaily.layoutManager=LinearLayoutManager(requireContext())
        recyclerViewForDaily.adapter=dailyListAdapter

        Log.i("TAG", "home: ")

        //        //initalize  viewModel DB
        viewModel.liveWeatherData.observe(viewLifecycleOwner, Observer {
                data ->data?.let {
            data.let { updaMainLayout(it) }
            data.hourly?.let { it1 -> updateHourlyListUI(it1) }
            data.daily?.let { it1 -> updateDailyListUI(it1) }
            data.let { updateDetailsLayout(it) }

        }
        })
/*
        val homeViewModel = ViewModelProvider(this).get(WeatherHomeViewModel::class.java)


           homeViewModel.liveWeatherData.observe(viewLifecycleOwner, Observer { data ->data?.let {
            data.let { updaMainLayout(it) }
            data.hourly?.let { it1 -> updateHourlyListUI(it1) }
            data.daily?.let { it1 -> updateDailyListUI(it1) }
            data.let { updateDetailsLayout(it) }
*
        } })
       // initUI()
**/
        return view
    }

    fun getIcon(icon: String): String {
        return "http://openweathermap.org/img/w/${icon}.png"
    }
    private fun initUI() {
        recycler_view_hourly.apply {
            layoutManager = LinearLayoutManager(activity)
       //     adapter = hourlyListAdapter
        }
    }
    private fun updateHourlyListUI(it: List<Hourly>) {
        hourlyListAdapter.updateHoursList(it)
    }
    private fun updateDailyListUI(it: List<Daily>) {
        dailyListAdapter.updateHoursList(it)
    }
    private fun updateDetailsLayout(it:WeatherDataModel){
        data_windSpeed.text= it.current?.windSpeed.toString()
        data_cloudCover.text=it.current?.clouds.toString()
        data_humidity.text=it.current?.humidity.toString()
        data_visibility.text=it.current?.visibility.toString()
        data_pressure.text=it.current?.pressure.toString()
        data_precipitation.text= it.minutely?.get(0)?.precipitation.toString()
    }
    private fun updaMainLayout(it: WeatherDataModel){
        city.text= it.timezone?.substringAfter("/")
        val calendar = Calendar.getInstance()
        val tz = TimeZone.getDefault()
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm a", Locale.getDefault())
        val currenTimeZone = (it.current?.dt?.toLong())?.times(1000)?.let { it1 -> Date(it1) }
        date_time.text= sdf.format(currenTimeZone)
        val options = RequestOptions()
            .error(R.mipmap.ic_launcher_round)
        val icon:String?= it.current?.weather?.get(0)?.icon
        context?.let { it1 ->
            Glide.with(it1)
                .setDefaultRequestOptions(options)
                .load(icon?.let { it2 -> getIcon(it2) })
                .into(imageView)
        }

        cloud_desc.text= it.current?.weather?.get(0)?.description
        max.text= "max: "+(it.daily?.get(0)?.temp?.max)?.toInt().toString()
        min.text= "min: "+ (it.daily?.get(0)?.temp?.min)?.toInt().toString()
        precipitation.text= "prec"+(it.minutely?.get(0)?.precipitation)?.toInt().toString()+"%"
        windSpeed.text="wind"+(it.current?.windSpeed?.toInt()).toString()+"mph"
        clouds.text= "clods"+ it.current?.clouds.toString()+"%"

        val tempr =(it.current?.temp)?.toInt().toString()+ "\u2103"
        val tempSpan:SpannableString= SpannableString(tempr)
        if (tempr.length >0){
            //the symbol will be smaller then the number
            tempSpan.setSpan( RelativeSizeSpan(0.7f),tempr.length-1, tempr.length, 0);

            //the number style will be bold and the symbol normal
            tempSpan.setSpan(android.text.style.StyleSpan(Typeface.BOLD), 0, tempr.length-1, 0);

            //the symbol color will be yellow
            tempSpan.setSpan( ForegroundColorSpan(Color.BLACK), tempr.length-1, tempr.length, 0);
        }
        temp.text=tempSpan
    }

}