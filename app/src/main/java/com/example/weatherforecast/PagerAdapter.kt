package com.example.weatherforecast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.weatherforecast.view.favouriteLoactions.Favourite
import com.example.weatherforecast.view.home.Home
import com.example.weatherforecast.view.weatherAlerts.Alert
import com.example.weatherforecast.view.setting.SettingsFragment

class PagerAdapter (fm: FragmentManager,noOfTabs:Int) : FragmentStatePagerAdapter(fm, noOfTabs) {

    private val numberOfTabs=noOfTabs
     override fun getItem(position: Int): Fragment {
         return when (position) {
             0 -> SettingsFragment()
             1 -> Home()
             2 -> Alert()
             3 -> Favourite()
             else-> Fragment()

         }
     }

     override fun getCount(): Int {
         return numberOfTabs
     }

 }