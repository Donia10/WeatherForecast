package com.example.weatherforecast.view.setting

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.weatherforecast.R
import com.example.weatherforecast.WeatherApplication
import com.example.weatherforecast.view.favouriteLoactions.MapsActivity
import com.example.weatherforecast.viewmodel.SettingViewModel
import com.example.weatherforecast.viewmodel.SettingViewModelFactory
import java.io.IOException
import java.util.*

class SettingsFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_preferences, rootKey)

      val settingPref=
          SettingPreferences(this)
        settingPref.updatePrefSummary(this)

    }

//     override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        // Inflate the layout for this fragment
//        return onCreateView(inflater,container,savedInstanceState)
////        val prefLoc: Preference? = getPreferenceScreen().findPreference("showLoc")
////        prefLoc?.setOnPreferenceChangeListener(Preference.OnPreferenceChangeListener { preference, newValue ->
////            if(preference.summary=="Search"){
////      //          navigateToMapsActivity()
////            }
////            true
////        })
//
//
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
////        val settingViewModel : SettingViewModel by viewModels {
////            SettingViewModelFactory( (requireActivity().application as WeatherApplication).repository)
////        }
//      //  loadSetting()
//     //   settingViewModel.getSP(context)
//
//    }
//
//     fun loadSetting() :SharedPreferences?{
//        val sp: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(context)
//        val temp: String? = sp?.getString("Temperature","notfound")
//        val  windspeed: String?=sp?.getString("WindSpeed","no")
//        val lan:String?=sp?.getString("language","not")
//
//        val loc:String?=sp?.getString("Location","not")
//         return sp
//    }
//
////    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
////        super.onActivityResult(requestCode, resultCode, data)
//////        if (requestCode==2&&resultCode== Activity.RESULT_OK){
//////            val latitude=data?.getDoubleExtra(MapsActivity.LaLon_Latitude, 0.0)
//////            val longitude=data?.getDoubleExtra(MapsActivity.LaLon_Longitude, 0.0)
//////            //     Toast.makeText(context,"$latitude ,$longitude",Toast.LENGTH_LONG).show()
//////            Log.i("TAG", "onActivityResult: $latitude ,$longitude")
//////            if (longitude != null&&latitude!=null) {
//////
//////                val prefLoc: Preference? = getPreferenceScreen().findPreference("showLoc")
//////                prefLoc?.setSummary(getAddress(latitude,longitude))
//////                getAddress(latitude,longitude)?.let { sharedPrefs(it,latitude,longitude) }
//////
//////
//////            }
//////        }else{
//////            Toast.makeText(context, "result null", Toast.LENGTH_SHORT).show()
//////        }
////    }
//
//    private fun sharedPrefs(location:String,lat:Double,lon:Double){
//        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
//        var editor:SharedPreferences.Editor= prefs.edit()
//        editor.putString("Location",location)
//        editor.putString("lat",lat.toString())
//        editor.putString("lon",lon.toString())
//        editor.commit();
//    }
//    private fun navigateToMapsActivity() {
//        val intent:Intent= Intent(context,MapsActivity::class.java)
//        startActivityForResult(intent,2)
//    }
}