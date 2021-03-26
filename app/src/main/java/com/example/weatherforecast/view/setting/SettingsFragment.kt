package com.example.weatherforecast.view.setting
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
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.weatherforecast.Language
import com.example.weatherforecast.R
import com.example.weatherforecast.view.favouriteLoactions.MapsActivity
import java.io.IOException
import java.util.*


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_preferences, rootKey)
      val settingPref=
          SettingPreferences(this)
        settingPref.updatePrefSummary(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val prefLang: Preference? =getPreferenceScreen().findPreference("language")
        val prefLocation: Preference? =getPreferenceScreen().findPreference("location")

        prefLang?.onPreferenceChangeListener=Preference.OnPreferenceChangeListener { preference, newValue ->
            Language.setLocale(requireActivity(), newValue.toString())
            requireActivity().recreate()

            true

        }
        prefLocation?.onPreferenceChangeListener=Preference.OnPreferenceChangeListener{preference, newValue ->

            if(newValue=="GPS") {
                val location = GetLocation(requireContext())
                location.getLastLocation()
            }else if(newValue=="Map"){
                val intent=Intent(requireContext(),MapsActivity::class.java)
                startActivityForResult(intent,2)
            }
            if (preference is ListPreference){
                val listPreference = preference
                val index = listPreference.findIndexOfValue(newValue.toString())
                preference.setSummary(
                    listPreference.entries[index])
            }
            true
        }
        return super.onCreateView(inflater, container, savedInstanceState)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==2&&resultCode== Activity.RESULT_OK){
            val latitude=data?.getDoubleExtra(MapsActivity.LaLon_Latitude, 0.0)
            val longitude=data?.getDoubleExtra(MapsActivity.LaLon_Longitude, 0.0)
            //     Toast.makeText(context,"$latitude ,$longitude",Toast.LENGTH_LONG).show()
            Log.i("TAG", "onActivityResult: $latitude ,$longitude")
            if (longitude != null&&latitude!=null) {
                getAddress(latitude,longitude)?.let { sharedPrefs(it,latitude,longitude) }
           //     Toast.makeText(context,getAddress(latitude,longitude),Toast.LENGTH_LONG).show()
                Log.i("TAG", "onActivityResult: ${getAddress(latitude,longitude)}")

            }
        }else{
   //         Toast.makeText(context, "result null",Toast.LENGTH_SHORT).show()
        }
    }
    private fun getAddress(latitude:Double , longitude:Double):String?{
        val geocoder: Geocoder
        var addresses :List<Address>?=null
        geocoder =  Geocoder(context, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);// Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch ( e: IOException) {
            e.printStackTrace();
        }
        return addresses?.get(0)?.getAddressLine(0)// If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

    }
    private fun sharedPrefs(location:String,lat:Double,lon:Double){
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var editor:SharedPreferences.Editor= prefs.edit()
        editor.putString("Location",location)
        editor.putString("lat",lat.toString())
        editor.putString("lon",lon.toString())
        editor.commit();
    }
}