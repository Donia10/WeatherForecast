package com.example.weatherforecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.room.Room
import androidx.viewpager.widget.ViewPager
import com.example.weatherforecast.model.WeatherApplication
import com.example.weatherforecast.viewmodel.WeatherHomeViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolBar =findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)
        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.addTab(tabLayout.newTab().setText(R.string.setting_fragment))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.home_fragment))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.alert_fragment))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.favourite_fragment))
        tabLayout.setTabMode(TabLayout.MODE_FIXED)

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)
        val viewPager =findViewById<ViewPager>(R.id.pager)
         val adapter=PagerAdapter(supportFragmentManager,tabLayout.tabCount)
        viewPager.setAdapter(adapter)

        //listener
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }
}