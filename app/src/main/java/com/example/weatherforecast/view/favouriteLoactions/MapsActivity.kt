package com.example.weatherforecast.view.favouriteLoactions

import android.app.Activity
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.weatherforecast.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val toolBar =findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        val home:LatLng= LatLng(29.986844, 32.555545)
        val zoom=10f
        mMap.addMarker(MarkerOptions().position(home).title("Home"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home,zoom))
        setMapClick(mMap)
        setPoiClick(mMap)


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.map_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.normal_map ->{
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                return true
            }
            R.id.hybrid_map ->{
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                return true
            }
            R.id.satellite_map ->{
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                return true
            }
            R.id.terrain_map ->{
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                return true
            }
            else ->return super.onOptionsItemSelected(item)
        }
    }
    private fun setMapClick(map: GoogleMap){
   //     Log.i("TAG", "setMapLongClick:")
        map.setOnMapClickListener{
        // return LaLong when user touch location
            Log.i("TAG", "setOnMapClickListener: $it")
            val snippet: String = java.lang.String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                it.latitude,
                it.longitude
            )
            map.addMarker(
                MarkerOptions()
                    .position(it)
                    .title(getAddress(it.latitude,it.longitude))
                    .snippet(snippet)
            )
     //       map.addMarker(MarkerOptions().position(it))

            it?.let {
                val reply:Intent=Intent()
                reply.putExtra(LaLon_Latitude,it.latitude)
                reply.putExtra(LaLon_Longitude,it.longitude)
                setResult(Activity.RESULT_OK,reply)
            }
        }


    }
    private fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener {
            val poiMarker=mMap.addMarker(MarkerOptions().position(it.latLng).title(it.name))
            poiMarker.showInfoWindow()
        }
    }
    companion object{
        const val  LaLon_Latitude="Latitude"
        const val  LaLon_Longitude="Longitude"

    }
    private fun getAddress( latitude:Double , longitude:Double):String?{
        val geocoder: Geocoder
        var addresses :List<Address>?=null
        geocoder =  Geocoder(applicationContext, Locale.getDefault());
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