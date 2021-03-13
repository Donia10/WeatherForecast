package com.example.weatherforecast.view.home
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weatherforecast.R
import com.example.weatherforecast.model.Daily
import kotlinx.android.synthetic.main.daily_row.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.hourly_row.view.*
import java.text.SimpleDateFormat
import java.util.*


class DailyListAdapter(var dailyList:ArrayList<Daily>) :RecyclerView.Adapter<DailyListAdapter.DailyViewHolder>(){


    fun updateHoursList(newDailyList: List<Daily>) {
        dailyList.clear()
        dailyList.addAll(newDailyList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        DailyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.daily_row, parent, false)
        )


    override fun getItemCount(): Int {
        return dailyList.size
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bind(dailyList[position])
    }

    class DailyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dt=itemView.home_day_temp_daily_item
        private val dailyDayIcon=itemView.home_day_icon_daily_item
        private val dailyDayTemp=itemView.home_day_temp_daily_item
        private val sunrise=itemView.home_day_sunrise_item
        private val dailyNightIcon=itemView.home_night_icon_daily_item
        private val dailyNightTemp=itemView.home_night_temp_daily_item
        private val sunset=itemView.home_night_sunset_daily_item
        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(itemView.home_day_icon_daily_item.getContext())
        fun bind(daily: Daily){
            val temp= sp.getString("Temperature","").toString()
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getDefault()
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val sdf = SimpleDateFormat(" EE:dd:MM", Locale.getDefault())
            val currenTimeZone = (daily.dt?.toLong())?.times(1000)?.let { it1 -> Date(it1) }
            dt.text= sdf.format(currenTimeZone)

            sunrise.text=getDt(daily.sunrise)
            sunset.text=getDt(daily.sunset)
            if(temp=="Fahrenheit"){
                dailyDayTemp.text=(daily.temp?.day)?.toInt().toString()+" "+ "\u2109"
            }else if(temp=="Celsius"){
                dailyDayTemp.text=(daily.temp?.day)?.toInt().toString()+ " "+"\u2103"
            }else{
                dailyDayTemp.text=(daily.temp?.day)?.toInt().toString()+" "+ "\u00B0"
            }
            if(temp=="Fahrenheit"){
                dailyNightTemp.text=(daily.temp?.night)?.toInt().toString()+" "+ "\u2109"
            }else if(temp=="Celsius"){
                dailyNightTemp.text=(daily.temp?.night)?.toInt().toString()+ " "+"\u2103"
            }else{
                dailyNightTemp.text=(daily.temp?.night)?.toInt().toString()+" "+ "\u00B0"
            }

            //   hourlyPrec.text=hourly.
            val dayIcon=daily.weather?.get(0)?.icon
            val options = RequestOptions()
                .error(R.mipmap.ic_launcher_round)
            Glide.with(dailyDayIcon.context)
                .setDefaultRequestOptions(options)
                .load(dayIcon?.let { it2 -> getIcon(it2) })
                .into(dailyDayIcon)

            val nightIcon=daily.weather?.get(0)?.icon?.substring(0,2)+"n"
            //10d -- 10n

            Log.i("TAG", "bind: $nightIcon")
            Glide.with(dailyNightIcon.context)
                .setDefaultRequestOptions(options)
                .load(nightIcon?.let { it3 -> getIcon(it3) })
                .into(dailyNightIcon)

        }
        fun getIcon(icon: String): String {
            return "http://openweathermap.org/img/w/${icon}.png"
        }
        fun getDt(dt:Int?):String{
        val calendar = Calendar.getInstance()
        val tz = TimeZone.getDefault()
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
        val sdf = SimpleDateFormat("HH:mm a", Locale.getDefault())
        val currenTimeZone = (dt?.toLong())?.times(1000)?.let { it1 -> Date(it1) }
        return sdf.format(currenTimeZone)
    }
    }
}