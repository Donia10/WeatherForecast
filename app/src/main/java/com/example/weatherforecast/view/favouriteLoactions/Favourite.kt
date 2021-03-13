package com.example.weatherforecast.view.favouriteLoactions

import android.app.Activity
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.R
import com.example.weatherforecast.WeatherApplication
import com.example.weatherforecast.viewmodel.WeatherFavouriteListViewModelFactory
import com.example.weatherforecast.viewmodel.WeatherFavouriteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_favourite.view.*
import java.io.IOException
import java.util.*


class Favourite : Fragment(),FavouriteLocationsListAdapter.OnViewClickListener {
    val weatherFavouriteViewModel : WeatherFavouriteViewModel by viewModels {
        WeatherFavouriteListViewModelFactory( (requireActivity().application as WeatherApplication).repository)
    }
    val adapter= FavouriteLocationsListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =inflater.inflate(R.layout.fragment_favourite, container, false)
        val recyclerView=view.favListRecyclerview

        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(context)

    weatherFavouriteViewModel.liveFavLocations.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
        Toast.makeText(context,"${it.size}",Toast.LENGTH_SHORT).show()
        adapter.submitList(it)
        adapter.setOnClickListener(this)
    })


        val fab=view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{

            val intent= Intent(requireContext() , MapsActivity::class.java)
            startActivityForResult(intent,1)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1&&resultCode==Activity.RESULT_OK){
            val latitude=data?.getDoubleExtra(MapsActivity.LaLon_Latitude, 0.0)
            val longitude=data?.getDoubleExtra(MapsActivity.LaLon_Longitude, 0.0)
       //     Toast.makeText(context,"$latitude ,$longitude",Toast.LENGTH_LONG).show()
            Log.i("TAG", "onActivityResult: $latitude ,$longitude")
            if (longitude != null&&latitude!=null) {

                weatherFavouriteViewModel.refreshFavDataFromRepository(latitude,longitude)
                Toast.makeText(context,getAddress(latitude,longitude),Toast.LENGTH_LONG).show()
                Log.i("TAG", "onActivityResult: ${getAddress(latitude,longitude)}")

            }
        }else{
            Toast.makeText(context, "result null",Toast.LENGTH_SHORT).show()
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

    override fun removeFromRoom(position: Int) {
    //    weatherAlertViewModel.deleteAlarm(adapter.currentList.get(position))

    }

    override fun navigateToWeatherData(position: Int) {
        Toast.makeText(requireContext(),"zz",Toast.LENGTH_SHORT).show()
        val intent= Intent(requireContext() , WeatherDataFav::class.java)

        intent.putExtra("latOfSelectedFavObject", adapter.currentList.get(position).lat)
        intent.putExtra("latOfSelectedFavObject", adapter.currentList.get(position).lat)
        intent.putExtra("lonOfSelectedFavObject", adapter.currentList.get(position).lon)
        startActivity(intent)
    }
}