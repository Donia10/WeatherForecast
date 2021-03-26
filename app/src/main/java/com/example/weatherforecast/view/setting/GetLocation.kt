package com.example.weatherforecast.view.setting

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.preference.PreferenceManager
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnCompleteListener
import java.io.IOException
import java.util.*

class GetLocation (con: Context){
    private val context:Context=con
    val PERMISSION_ID = 10

    var latitude:Double=0.0
    var longitude:Double=0.0
    var address:String=""
    var lalong:String?=null

     var fusedLocationProviderClient: FusedLocationProviderClient? =null

       fun getLastLocation() :LatLng?{
           var latLng:LatLng= LatLng(latitude,longitude)
            fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(context)
            if(checkPermission()){
                if(checkLocationEnabled()){
                    fusedLocationProviderClient?.getLastLocation()?.addOnCompleteListener(OnCompleteListener {
                        var location:android.location.Location=it.getResult()
                        if(location==null){

                            Toast.makeText(context,"..",Toast.LENGTH_SHORT).show()
                            requestNewLocationData()
                        }else {
                            latLng = LatLng(location.latitude, location.longitude)
                            latitude = location.getLatitude()
                            longitude = location.getLongitude()
                            //     t.setText(location.getLatitude() +"--"+location.getLongitude());
                                   Toast.makeText(context, ("longitude=  "+longitude+" latitude "+latitude),Toast.LENGTH_LONG).show();
                            if (it.isSuccessful) {
                                sharedPrefs("ssssss",location.latitude,location.longitude)
                                getLaLong(latitude, longitude)
                                lalong = sendLaLong()
                            }
                       }
                    })
                } else {
                    Toast.makeText(context, "turn on location", Toast.LENGTH_SHORT).show();
                    val intent:Intent =  Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(intent)
                }

            } else {
                requestPermission()
            }
           return latLng
         }
        @SuppressLint("MissingPermission")
        private fun requestNewLocationData() {
            val locationRequest: LocationRequest? =null
            locationRequest?.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            locationRequest?.setInterval(0)
            locationRequest?.setNumUpdates(1)
            fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(context);

          fusedLocationProviderClient?.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
        }
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val location = locationResult.lastLocation
            Toast.makeText(
                context, "Latitude" + location.latitude +
                        "Longatitude" + location.longitude, Toast.LENGTH_SHORT
            ).show()
        }
    }
        private fun checkLocationEnabled():Boolean {
            val locationManager: LocationManager? = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
            if (locationManager != null) {
                return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            }
            return false
        }
        private fun checkPermission ():Boolean{
            if (ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {
               return true;
            }
            return false;
        }
        private fun requestPermission(){
            ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION),PERMISSION_ID)


        }
         private fun getAddress(){
            val geocoder: Geocoder
            var addresses :List<Address>?=null
            geocoder =  Geocoder(context, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);// Here 1 represent max location result to returned, by documents it recommended 1 to 5
                //      t.setText(address);
            } catch ( e: IOException) {
                e.printStackTrace();
            }
            address = addresses?.get(0)?.getAddressLine(0).toString() // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                    Toast.makeText(context, "address is " + address, Toast.LENGTH_SHORT).show().toString();
            //     t.setText(address);

        }
      fun getLaLong(lati:Double,long:Double){
   //      Toast.makeText(context, "lati $lati and long $long ",Toast.LENGTH_LONG).show()
          lalong="$lati/$long"
     }
    fun sendLaLong():String?{
   //     Toast.makeText(context, "lalong $lalong ",Toast.LENGTH_LONG).show()
        getAddress()

        return "address "+address+lalong
    }
    private fun sharedPrefs(location: String,lat:Double,lon:Double){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        var editor:SharedPreferences.Editor= prefs.edit()
        editor.putString("Location",location)
        editor.putString("lat",lat.toString())
        editor.putString("lon",lon.toString())
        editor.commit();
    }

}