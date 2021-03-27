package com.example.weatherforecast

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.app.NotificationCompat
import androidx.preference.PreferenceManager
import com.example.weatherforecast.model.Alert
import com.example.weatherforecast.model.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmReceiver : BroadcastReceiver() {
    private val NOTIFICATION_ID = 0
    private val PRIMARY_CHANNEL_ID="primary_notification_channel"
    private lateinit var notificationManager: NotificationManager
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        var event:String?=null
        var id:Int?=0
        var aletobj:Alert?=null
        notificationManager= context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        CoroutineScope(Dispatchers.IO).launch {
            var repo=WeatherRepository(((context.applicationContext as WeatherApplication).database).weatherDao())
            repo.refreshWeatherData(context)
           val alert = repo.getAlert(context)?.alerts?.get(0)?.description
            aletobj=repo.getAlert(context)?.alerts?.get(0)
            event=intent.getStringExtra("event")
            id=intent.getIntExtra("id",0)
            if(event!=null){
                if (alert != null) {
                    if (alert.contains(event!!))
                    {
                        id?.let { deliverNotification(context,aletobj, it) }
                    }
                }
            }
            id?.let { deliverNotification(context,aletobj, it) }

        }
    }

    private fun deliverNotification(context: Context, get: Alert?,id:Int){
        val contentIntent:Intent=Intent(context,MainActivity::class.java)
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(context,id,contentIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder: NotificationCompat.Builder=
            NotificationCompat.Builder(context,PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("Weather Alert for event ${get?.event}")
                .setContentTitle("Weather Alert for event ${get?.event}")
                .setContentText("${get?.description}")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
        notificationManager.notify(id,builder.build())
    }

}