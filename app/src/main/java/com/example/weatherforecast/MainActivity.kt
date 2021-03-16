package com.example.weatherforecast

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolBar =findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)
        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val lan=sp.getString("language","")
        Language.setLocale(this,lan)
        Toast.makeText(this,lan,Toast.LENGTH_SHORT).show()

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