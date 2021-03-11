package com.example.weatherforecast

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import java.util.*

object Language {
    fun setLocale(activity: Activity, languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}