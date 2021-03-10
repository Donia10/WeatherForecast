package com.example.weatherforecast.view.weatherAlerts

import android.app.*
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.weatherforecast.AlarmReceiver
import com.example.weatherforecast.R
import kotlinx.android.synthetic.main.fragment_alert.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Alert : Fragment() ,EventsCustomDialogFragment.EventsListener{
    private val NOTIFICATION_ID = 0
    private val PRIMARY_CHANNEL_ID="primary_notification_channel"
    private lateinit var notificationManager:NotificationManager
    private lateinit var toastMsg:String

    private  var eventChosen:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View= inflater.inflate(R.layout.fragment_alert, container, false)
        val alarmToggleButton:ToggleButton=view.alarmToggle
        notificationManager= activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val timetext:TextView=view.txt_time
        val setTime:Button=view.setTime
        val alertEvent=view.alert_fab
        val calendar:Calendar= Calendar.getInstance()

        alertEvent.setOnClickListener(View.OnClickListener {
            showEventAlert()
        })
        var text="Alarm set for :"
        timetext.text=text+DateFormat.getTimeInstance(DateFormat.SHORT).format(System.currentTimeMillis())
        setTime.setOnClickListener(View.OnClickListener {
            val timePickerDialog =
                TimePickerDialog(context,
                    OnTimeSetListener { timePicker, selectedHours, selectedMinute ->
                        calendar.set(Calendar.HOUR_OF_DAY,selectedHours)
                        calendar.set(Calendar.MINUTE,selectedMinute)
                        calendar.set(Calendar.SECOND,0)

                        text+=DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.time)
                        timetext.text=text
                  //      setAlarm(calendar)


                    }, 12, 0, false
                )
            timePickerDialog.show()

                    })

        alarmToggleButton.setOnCheckedChangeListener(
            CompoundButton.OnCheckedChangeListener { compoundButton, isChecked ->
                if (isChecked) {

                    setAlarm(calendar)
                    toastMsg = "Stand Up Alarm On"

                } else {
                    cancelAlarm()
                    toastMsg = "Stand Up Alarm Off"
                }
                Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
            })

        createNotificationChannel()

        return view

    }

    private fun showEventAlert() {
       /* var eventDialog=EventsCustomDialogFragment()
        activity?.supportFragmentManager?.let { eventDialog.show(it,"event") }*/
        var dialog=EventsCustomDialogFragment()
        dialog.setTargetFragment(this,1)
        fragmentManager?.let { dialog.show(it,"eventDialog") }
    }

    public fun createNotificationChannel(){
        //create notification manager object
        notificationManager= activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //check on skd version
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            //create notification channel with all parameters
            val notificationChannel:NotificationChannel=
                NotificationChannel(PRIMARY_CHANNEL_ID,"stand up Notification",NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.setLightColor(Color.RED)
            notificationChannel.enableVibration(true)
            notificationChannel.setDescription("notification every 15 min")

            notificationManager.createNotificationChannel(notificationChannel)
        }

    }
    private fun setAlarm(calendar: Calendar){
        val notifyIntent:Intent=Intent(context, AlarmReceiver::class.java)
        if (eventChosen!=null)
        notifyIntent.putExtra("event",eventChosen)
        val alarmManager:AlarmManager=activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val notifyPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(context,NOTIFICATION_ID,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT)

//Alarm Manager
        //set repeating Alarm
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,notifyPendingIntent)

    }
    private fun cancelAlarm(){
          notificationManager.cancelAll()
        val notifyIntent:Intent=Intent(context, AlarmReceiver::class.java)
        if (eventChosen!=null)
            notifyIntent.putExtra("event",eventChosen)
        val alarmManager:AlarmManager=activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val notifyPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(context,NOTIFICATION_ID,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT)

        if (alarmManager!=null){
                       alarmManager.cancel(notifyPendingIntent)
                   }
    }
    private fun updateTimeText(calendar: Calendar){
        var text="Alarm set for :"
        text+=DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar)

    }

    override fun getEvent(event: String) {
        eventChosen=event
    //   Toast.makeText(requireContext(),"$eventChosen",Toast.LENGTH_SHORT).show()

    }

}
