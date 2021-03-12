package com.example.weatherforecast.view.favouriteLoactions
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.dynamicanimation.animation.FloatValueHolder
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.model.WeatherDataModel
import com.example.weatherforecast.model.local.AlarmData
import com.example.weatherforecast.view.weatherAlerts.AlarmAdapter
import kotlinx.android.synthetic.main.fav_list_item.view.*
import java.io.IOException
import java.util.*

class FavouriteLocationsListAdapter :ListAdapter <WeatherDataModel, FavouriteLocationsListAdapter.FavLocationViewHolder>(LocationsComparator()) {

    private lateinit var  context:Context
    lateinit var listener:FavouriteLocationsListAdapter.OnViewClickListener

    interface OnViewClickListener {

        fun removeFromRoom(position: Int)
        fun navigateToWeatherData(position: Int)

    }
    fun setOnClickListener( listenerr: OnViewClickListener){listener=listenerr}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavLocationViewHolder {
      val view=  LayoutInflater.from(parent.context).inflate(R.layout.fav_list_item, parent, false)
        context=parent.context
        return FavLocationViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: FavLocationViewHolder, position: Int) {
        val current=getItem(position)
        holder.bind(getAddress(current.lat,current.lon))
       /* holder.itemView.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(requireContext(),WeatherDataForFavLocation::class.java)
//            startActivity(intent)
        })*/
    }

    class FavLocationViewHolder(itemView: View,val listener:OnViewClickListener) : RecyclerView.ViewHolder(itemView) {

        private val locationNameItemView :TextView=itemView.location_name
        fun bind(text:String?){
            locationNameItemView.text=text
            itemView.setOnClickListener(View.OnClickListener {
                if(listener!=null) {
                    val position: Int = adapterPosition
                    if (position!=RecyclerView.NO_POSITION) {
                      //  listener.removeFromRoom(position)
                        listener.navigateToWeatherData(position)

                    }
                }
            })

        }

    }

    class LocationsComparator : DiffUtil.ItemCallback<WeatherDataModel>(){
        override fun areItemsTheSame(oldItem: WeatherDataModel, newItem: WeatherDataModel): Boolean {
            return oldItem===newItem
        }

        override fun areContentsTheSame(oldItem: WeatherDataModel, newItem: WeatherDataModel): Boolean {
            return oldItem.lat==newItem.lat && oldItem.lon ==newItem.lon
        }

    }
   private fun getAddress( latitude:Double , longitude:Double):String?{
       val geocoder: Geocoder
       var addresses :List<Address>?=null
       geocoder =  Geocoder(context, Locale.getDefault());
       try {
           addresses = geocoder.getFromLocation(latitude, longitude, 1);// Here 1 represent max location result to returned, by documents it recommended 1 to 5
           //      t.setText(address);
       } catch ( e: IOException) {
           e.printStackTrace();
       }
       return addresses?.get(0)?.getAddressLine(0)// If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
       //     t.setText(address);

   }
}