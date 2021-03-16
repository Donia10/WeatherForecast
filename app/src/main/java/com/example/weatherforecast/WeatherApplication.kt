package com.example.weatherforecast

import android.app.Application
import android.content.Intent
import com.example.roomwordsample.model.WeatherDatabase
import com.example.weatherforecast.model.WeatherRepository
import java.util.Locale;
import android.app.Activity;
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate


class WeatherApplication:Application() {
     val database by lazy {  WeatherDatabase.getDatabase(this)}
    val repository by lazy {
        WeatherRepository(
            database.weatherDao()
        )
    }

//    override fun attachBaseContext(base: Context) {
//        AppPreferences.init(base)
//        super.attachBaseContext(Language.setLocale(base))
//    }
//    companion object {        lateinit var appContext: Context    }
//    init {        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)    }
//    override fun onCreate() {
//        super.onCreate()
//        appContext = applicationContext
//        AppPreferences.init(appContext)
//        LanguageManager.setLocale(this)
//    }
//
    fun setLocale( activity: Activity,languageCode: String?) {
        val myLocale = Locale(languageCode)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(activity, MainActivity::class.java)
        activity.finish()
        startActivity(refresh)

    }
    fun setLocale(languageCode: String?) {

        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = this.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}