package com.example.weatherforecast

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import kotlin.random.Random.Default.Companion
import kotlinx.coroutines.*

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_preferences, rootKey)

      val settingPref=SettingPreferences(this)
        settingPref.updatePrefSummary(this)


    }
}