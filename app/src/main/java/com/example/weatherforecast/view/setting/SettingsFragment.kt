package com.example.weatherforecast.view.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.weatherforecast.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_preferences, rootKey)

      val settingPref=
          SettingPreferences(this)
        settingPref.updatePrefSummary(this)


    }
}