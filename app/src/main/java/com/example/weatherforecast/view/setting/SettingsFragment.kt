package com.example.weatherforecast.view.setting

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.weatherforecast.R
import com.example.weatherforecast.WeatherApplication
import com.example.weatherforecast.viewmodel.SettingViewModel
import com.example.weatherforecast.viewmodel.SettingViewModelFactory

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

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val settingViewModel : SettingViewModel by viewModels {
            SettingViewModelFactory( (requireActivity().application as WeatherApplication).repository)
        }
      //  loadSetting()
     //   settingViewModel.getSP(context)

    }

     fun loadSetting() :SharedPreferences?{
        val sp: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(context)
        val temp: String? = sp?.getString("Temperature","notfound")
        val  windspeed: String?=sp?.getString("WindSpeed","no")
        val lan:String?=sp?.getString("language","not")

        val loc:String?=sp?.getString("Location","not")
         return sp
    }


}