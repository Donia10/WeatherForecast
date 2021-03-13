package com.example.weatherforecast.view.favouriteLoactions
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weatherforecast.R
import com.example.weatherforecast.model.Hourly
import kotlinx.android.synthetic.main.hourly_fav_row.view.*
import kotlinx.android.synthetic.main.hourly_row.view.*
import java.text.SimpleDateFormat
import java.util.*


class FavHourlyListAdapter(var hoursList:ArrayList<Hourly>) :RecyclerView.Adapter<FavHourlyListAdapter.HourlyViewHolder>(){


    fun updateHoursList(newCountries: List<Hourly>) {
        hoursList.clear()
        hoursList.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        HourlyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.hourly_fav_row, parent, false)
        )


    override fun getItemCount(): Int {
        return hoursList.size
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.bind(hoursList[position])
    }

    class HourlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val hourlyTime=itemView.fav_hourly_item
        private val hourlyIcon=itemView.fav_icon_item
        private val hourlyTemp=itemView.fav_hourly_item_temp
        private val hourlyPrec=itemView.fav_hourly_item_clouds
        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(itemView.fav_hourly_item.getContext())
        fun bind(hourly:Hourly){
            val temp= sp.getString("Temperature","").toString()
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getDefault()
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val sdf = SimpleDateFormat("HH:mm a", Locale.getDefault())
            val currenTimeZone = (hourly.dt?.toLong())?.times(1000)?.let { it1 -> Date(it1) }
            hourlyTime.text= sdf.format(currenTimeZone)

            hourlyPrec.text="clouds:"+(hourly.clouds).toString()+"%"
            if(temp=="Fahrenheit"){
                hourlyTemp.text = (hourly.temp)?.toInt().toString()+" "+ "\u2109"
            }else if(temp=="Celsius"){
                hourlyTemp.text = (hourly.temp)?.toInt().toString()+" "+  "\u2103"
            }else{
                hourlyTemp.text = (hourly.temp)?.toInt().toString()+" "+ "\u00B0"
            }
            //   hourlyPrec.text=hourly.
            val icon=hourly.weather?.get(0)?.icon
            val options = RequestOptions()
                .error(R.mipmap.ic_launcher_round)
            Glide.with(hourlyIcon.context)
                .setDefaultRequestOptions(options)
                .load(icon?.let { it2 -> getIcon(it2) })
                .into(hourlyIcon)
        }
        fun getIcon(icon: String): String {
            return "http://openweathermap.org/img/w/${icon}.png"
        }

    }
}