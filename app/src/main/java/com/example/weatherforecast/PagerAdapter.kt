package com.example.weatherforecast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter (fm: FragmentManager,noOfTabs:Int) : FragmentStatePagerAdapter(fm, noOfTabs) {
     val numberOfTabs=noOfTabs

     override fun getItem(position: Int): Fragment {
            when (position) {
             0 -> return Setting()
             1 -> return Home()
             2 -> return Alert()
             3 -> return Favourite()
             else->return Fragment()

         }
     }

     override fun getCount(): Int {
         return numberOfTabs
     }

 }