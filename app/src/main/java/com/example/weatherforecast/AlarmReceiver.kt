package com.example.weatherforecast

import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.model.WeatherDataModel
import com.example.weatherforecast.model.WeatherRepository
import com.example.weatherforecast.model.remote.WeatherHomeService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlarmReceiver : BroadcastReceiver() {
    private val NOTIFICATION_ID = 0
    private val PRIMARY_CHANNEL_ID="primary_notification_channel"
    private lateinit var notificationManager: NotificationManager
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        notificationManager= context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        CoroutineScope(Dispatchers.IO).launch {
            var repo=WeatherRepository(((context.applicationContext as WeatherApplication).database).weatherDao())
            repo.refreshWeatherData()
       //     val alert=repo.getAlert().description
           var event=intent.getStringExtra("event")
       //     event?.let { alert?.contains(it) }
            deliverNotification(context,event)
        }
      //  deliverNotification(context,intent.getStringExtra("event"))

    }

    private fun deliverNotification(context: Context, alert: String?){
        val contentIntent:Intent=Intent(context,MainActivity::class.java)
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(context,NOTIFICATION_ID,contentIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder: NotificationCompat.Builder=
            NotificationCompat.Builder(context,PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("Weather Alert")
                .setContentText("weather alert from api,$alert")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
        notificationManager.notify(NOTIFICATION_ID,builder.build())
    }

}