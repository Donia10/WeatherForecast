package com.example.weatherforecast.view.weatherAlerts

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.AlarmReceiver
import com.example.weatherforecast.R
import com.example.weatherforecast.WeatherApplication
import com.example.weatherforecast.model.local.AlarmData
import kotlinx.android.synthetic.main.fragment_alert.view.*
import java.text.DateFormat
import java.util.*

class Alert : Fragment() ,EventsCustomDialogFragment.EventsListener,AddAlarmDialog.EventsListener ,AlarmAdapter.OnItemClickListener{
    private val NOTIFICATION_ID = 0
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    private lateinit var notificationManager: NotificationManager
    private lateinit var toastMsg: String
    val adapter = AlarmAdapter()
    val weatherAlertViewModel: WeatherAlertViewModel by viewModels {
        WeatherAlertViewModelFactory(
            (requireActivity().application as WeatherApplication).repository
        )
    }

    private var eventChosen: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_alert, container, false)
        val recyclerView = view.alert_recyclerview

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        notificationManager =
            activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        weatherAlertViewModel.alarmData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.submitList(it)
            adapter.setOnClickListener(this)

        })

        val alertEvent = view.alert_fab
        val calendar: Calendar = Calendar.getInstance()

        alertEvent.setOnClickListener(View.OnClickListener {
            showEventAlert()
        })


//        alarmToggleButton.setOnCheckedChangeListener(
//            CompoundButton.OnCheckedChangeListener { compoundButton, isChecked ->
//                if (isChecked) {
//
//                    setAlarm(calendar)
//                    weatherAlertViewModel.setAlarm(
//                        AlarmData(
//                            true,
//                            calendar.timeInMillis,
//                            "24h",
//                            "notification",
//                            "rain"
//                        )
//                    )
//                    toastMsg = "Stand Up Alarm On"
//
//                } else {
//                    cancelAlarm()
//                    toastMsg = "Stand Up Alarm Off"
//                }
//                Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
//            })

        createNotificationChannel()


        return view

    }

    private fun showEventAlert() {
        /* var eventDialog=EventsCustomDialogFragment()
        activity?.supportFragmentManager?.let { eventDialog.show(it,"event") }*/
        var dialog = AddAlarmDialog()
        dialog.setTargetFragment(this, 1)
        fragmentManager?.let { dialog.show(it, "AddDialog") }
    }

    public fun createNotificationChannel() {
        //create notification manager object
        notificationManager =
            activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //check on skd version
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //create notification channel with all parameters
            val notificationChannel: NotificationChannel =
                NotificationChannel(
                    PRIMARY_CHANNEL_ID,
                    "stand up Notification",
                    NotificationManager.IMPORTANCE_HIGH
                )
            notificationChannel.enableLights(true)
            notificationChannel.setLightColor(Color.RED)
            notificationChannel.enableVibration(true)
            notificationChannel.setDescription("notification every 15 min")

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun setAlarm(calendar: Calendar,id: Long) {
        val notifyIntent: Intent = Intent(context, AlarmReceiver::class.java)
        if (eventChosen != null)
            notifyIntent.putExtra("event", eventChosen)


        val alarmManager: AlarmManager =
            activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val i=(0..100).random()
        notifyIntent.putExtra("id", i)
        val notifyPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                context,
                i,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

//Alarm Manager
        //set repeating Alarm
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            notifyPendingIntent
        )

    }

    private fun cancelAlarm() {
        notificationManager.cancelAll()
        val notifyIntent: Intent = Intent(context, AlarmReceiver::class.java)
        if (eventChosen != null)
            notifyIntent.putExtra("event", eventChosen)
        val alarmManager: AlarmManager =
            activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val notifyPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                context,
                NOTIFICATION_ID,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        if (alarmManager != null) {
            alarmManager.cancel(notifyPendingIntent)
        }
    }

    private fun updateTimeText(calendar: Calendar) {
        var text = "Alarm set for :"
        text += DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar)

    }

    override fun getEvent(event: String) {
        eventChosen = event
        //   Toast.makeText(requireContext(),"$eventChosen",Toast.LENGTH_SHORT).show()

    }

    override fun getAlarmObject(alarmData: AlarmData,calendar: Calendar) {
        if (alarmData != null) {
            val id=weatherAlertViewModel.setAlarm(alarmData)
            Toast.makeText(requireContext(), "set Alarm Successfully", Toast.LENGTH_SHORT).show()
            startAlarm(calendar,id)
        }

        }

     fun startAlarm(calendar: Calendar,id:Long) {
        setAlarm(calendar,id)
    }

    override fun deleteFromApter(position: Int) {
      /*  adapter.currentList.removeAt(position)
        adapter.notifyDataSetChanged()*/
        Toast.makeText(requireContext(),"delete alarm ",Toast.LENGTH_SHORT).show()
    }

    override fun removeFromRoom(position: Int) {
        cancelAlarm()
        weatherAlertViewModel.deleteAlarm(adapter.currentList.get(position))

    }

    override fun removeAlarm(alarmData: AlarmData) {
        cancelAlarm()
        weatherAlertViewModel.deleteAlarm(alarmData)

    }
}