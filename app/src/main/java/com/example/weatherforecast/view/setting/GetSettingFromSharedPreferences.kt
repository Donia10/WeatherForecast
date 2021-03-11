package com.example.weatherforecast.view.setting

import android.app.Activity
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import com.google.android.material.internal.ContextUtils

class GetSettingFromSharedPreferences(activity: SettingsFragment) {

    val context=activity

//    val prefs = PreferenceManager.getDefaultSharedPreferences(activity.context)

    val prefLocation: Preference? = activity.getPreferenceScreen().findPreference("location")
    val prefTemp: Preference? = activity.getPreferenceScreen().findPreference("Temperature")
    val prefWindSpeed: Preference? = activity.getPreferenceScreen().findPreference("WindSpeed")
    val prefLang: Preference? = activity.getPreferenceScreen().findPreference("language")
    val prefSearch: Preference? = activity.getPreferenceScreen().findPreference("edit")

}