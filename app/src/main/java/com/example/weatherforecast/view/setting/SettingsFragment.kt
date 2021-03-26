package com.example.weatherforecast.view.setting
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.weatherforecast.Language
import com.example.weatherforecast.R
import com.example.weatherforecast.view.favouriteLoactions.MapsActivity


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
            }else if(newValue=="Search"){
                val intent=Intent(requireContext(),MapsActivity::class.java)
                startActivity(intent)
            }
            true
        }
        return super.onCreateView(inflater, container, savedInstanceState)

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