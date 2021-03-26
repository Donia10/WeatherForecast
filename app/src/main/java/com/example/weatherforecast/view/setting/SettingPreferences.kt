package com.example.weatherforecast.view.setting

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SettingPreferences(activity: SettingsFragment){
    val context=activity

    val prefLocation: Preference? = activity.getPreferenceScreen().findPreference("location")
    val prefTemp: Preference? = activity.getPreferenceScreen().findPreference("Temperature")
    val prefWindSpeed: Preference? = activity.getPreferenceScreen().findPreference("WindSpeed")
    val prefLang: Preference? = activity.getPreferenceScreen().findPreference("language")
    val prefSearch: Preference? = activity.getPreferenceScreen().findPreference("edit")

    fun updatePrefSummary(context: SettingsFragment) {
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
                                listPreference.entries[index]
                            } else

                                listPreference.summary)

                } else  {
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

}