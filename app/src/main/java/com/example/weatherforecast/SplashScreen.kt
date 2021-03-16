package com.example.weatherforecast

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.SyncStateContract
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            val i = Intent(
                this,
                MainActivity::class.java
            )
            //Intent is used to switch from one activity to another.
            startActivity(i)
            //invoke the SecondActivity.
            finish()
            //the current activity will get finished.
        }, 5000)


    }
}
//
//val config:EasySplashScreen=EasySplashScreen(this)
//    .withFullScreen()
//    .withTargetActivity(MainActivity::class.java)
//    .withSplashTimeOut(5000)
//    .withLogo(R.raw.weatheranimation)
//
//
//val easySplashScreen:View= config.create()
//setContentView(easySplashScreen)