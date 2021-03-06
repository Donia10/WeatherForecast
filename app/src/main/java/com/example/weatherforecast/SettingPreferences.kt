package com.example.weatherforecast

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.internal.ContextUtils.getActivity
import java.security.AccessController.getContext
import java.util.*
import kotlinx.coroutines.*

class SettingPreferences(activity: SettingsFragment){
    val context=activity
 //   val prefs:SharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE)
    public  val PREF_NAME:String="MY_PREF"
    @SuppressLint("RestrictedApi")
    val myPref= getActivity(activity.context)?.getSharedPreferences(PREF_NAME,0);

    val prefs = PreferenceManager.getDefaultSharedPreferences(activity.context)

    val prefLocation: Preference? = activity.getPreferenceScreen().findPreference("location")
    val prefTemp: Preference? = activity.getPreferenceScreen().findPreference("Temperature")
    val prefWindSpeed: Preference? = activity.getPreferenceScreen().findPreference("WindSpeed")
    val prefLang: Preference? = activity.getPreferenceScreen().findPreference("language")
    val prefSearch: Preference? = activity.getPreferenceScreen().findPreference("edit")

    fun updatePrefSummary(context:SettingsFragment) {
        get(context)
       // prefLocation?.let { bindPreferenceSummaryToValue(it) }
        if (prefLocation != null) {
            bindPreferenceSummaryToValue(prefLocation)

        }
        prefWindSpeed?.let { bindPreferenceSummaryToValue(it)
        }
        prefSearch?.let { bindPreferenceSummaryToValue(it) }
        prefTemp?.let { bindPreferenceSummaryToValue(it) }

        prefLang?.let { bindPreferenceSummaryToValue(it) }

        //  prefLocation?.setSummary(prefs.getString("location", "unset"))
    }
        /**
         * A preference value change listener that updates the preference's summary
         * to reflect its new value.
         */



     //   companion object {
            val instance : SettingsFragment? =null
            public fun get(context: SettingsFragment) {
                 val instance=context
                val prefSearch: Preference? = instance.getPreferenceScreen().findPreference("displayLocation")
             //  Places.initialize(getContext(), apiKey);

                x(prefSearch)
            }
            var search:Preference?=null
            fun x(pr:Preference?){
                search=pr
            }
            var flag = false
    var s:String?=null

    private val sBindPreferenceSummaryToValueListener = Preference.OnPreferenceChangeListener { preference, value ->
                var stringValue = value.toString()
                if (preference is ListPreference) {

                    // For list preferences, look up the correct display value in
                    // the preference's 'entries' list.
                    val listPreference = preference
                    val index = listPreference.findIndexOfValue(stringValue)
                    // Set the summary to reflect the new value.
                    preference.setSummary(
                            if (index >= 0) {
                                if (listPreference.entries[index] == "Search") {
                                    flag = true
                                    Toast.makeText(preference.context, "$index", Toast.LENGTH_SHORT).show()
                  //                  Log.i("TAG", "search: ${preference.isVisible}${com.example.weatherforecast.SettingPreferences.Companion.flag}")
                                    search?.let { it1 -> bindPreferenceSummaryToValue(it1) }

                                } else if (listPreference.entries[index] == "GPS") {
                                    flag = false
                                    CoroutineScope(Dispatchers.Default).launch {
                                        val getLocation = GetLocation(preference.context)
                                      val job=  launch {
                                           getLocation.getLastLocation()  }
                                        launch {   delay(5000)
                                            s=getLocation.sendLaLong()
                                            s?.let { sharedPrefs(it) }
                                        }

                                    }
                                    search?.let { it1 -> bindPreferenceSummaryToValue(it1) }
                                    //                 Log.i("TAG", "gps: ${preference.isVisible}${com.example.weatherforecast.SettingPreferences.Companion.flag}")

                                }
                                listPreference.entries[index]
                            } else

                                listPreference.summary)

                } else  {
                    // For all other preferences, set the summary to the value's
                    // simple string representation.
                    if(flag) {
                        preference.isVisible = true
                        preference.summary = stringValue

                    }
                    else if(flag==false) {
                        preference.isVisible = true
                        stringValue= s.toString()
                        preference.summary = prefs.getString("Location","not found")

                    }
                 //       Log.i("TAG", ": ${preference.isVisible} ${com.example.weatherforecast.SettingPreferences.Companion.flag}")
                       else
                        preference.summary = stringValue


                }
                true

            }
            private fun bindPreferenceSummaryToValue(preference: Preference) {
                // Set the listener to watch for value changes.
                preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

                // Trigger the listener immediately with the preference's
                // current value.
                sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                        PreferenceManager
                                .getDefaultSharedPreferences(preference.context)
                                .getString(preference.key, ""))

            }
    //    }

    private fun sharedPrefs(location:String){
        var editor:SharedPreferences.Editor= prefs.edit()
        editor.putString("Location",location)
        editor.commit();
    }

}