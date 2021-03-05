package com.example.weatherforecast.view.favouriteLoactions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.weatherforecast.R
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Favourite : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =inflater.inflate(R.layout.fragment_favourite, container, false)
        Log.i("TAG", "fav: ")
        val fab=view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{

            val intent= Intent(requireContext() , MapsActivity::class.java)
            startActivityForResult(intent,1)

            /*
            val gmmIntentUri =
                Uri.parse("https://www.google.com/maps/dir/?api=1&origin&travelmode=driving")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            requireContext().startActivity(mapIntent)
            **/

        }
        /*
        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.submitList(it) }
        })**/
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1&&resultCode==Activity.RESULT_OK){
            val latitude=data?.getDoubleExtra(MapsActivity.LaLon_Latitude, 0.0)
            val longitude=data?.getDoubleExtra(MapsActivity.LaLon_Longitude, 0.0)
            Toast.makeText(context,"$latitude ,$longitude",Toast.LENGTH_LONG).show()
            Log.i("TAG", "onActivityResult: $latitude ,$longitude")
        }else{
            Toast.makeText(context, "result null",Toast.LENGTH_SHORT).show()
        }

    }
}