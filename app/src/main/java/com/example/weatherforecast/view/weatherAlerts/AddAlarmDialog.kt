package com.example.weatherforecast.view.weatherAlerts

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.weatherforecast.R
import com.example.weatherforecast.model.local.AlarmData
import kotlinx.android.synthetic.main.add_alarm_dialog.view.*
import java.util.*


class AddAlarmDialog:DialogFragment() ,EventsCustomDialogFragment.EventsListener {
    private  var listener: EventsListener? =null
    var getevent:String?=null
    var isNotification:String="Notification"
    var repeat:Int=1
    private lateinit var eventSelected:EventsListener
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =inflater.inflate(R.layout.add_alarm_dialog, container, false)
        setWidthPercent(200)
        val wmlp = dialog!!.window!!.attributes
        wmlp.gravity = Gravity.CENTER
        setFullScreen()
        val save:Button=view.save
        val cancel:Button=view.cancel
        val timeBtn:ImageButton=view.time_dialog
        val dateBtn:ImageButton=view.date_dialog
        val eventBtn:ImageButton=view.event_dialog
        val noti:RadioButton=view.notification_dialog
        val default:RadioButton=view.defaultSound_dialog
        val one:RadioButton=view.one_dialog
        val two:RadioButton=view.two_dialog
        val three:RadioButton=view.three_dialog

        var calendar:Calendar=Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        var isSetTime:Boolean=false
        var isSetDate:Boolean=false
        timeBtn.setOnClickListener(View.OnClickListener {
            val timePickerDialog =
                TimePickerDialog(context,
                    OnTimeSetListener { timePicker, selectedHours, selectedMinute ->
                        isSetTime=true
                        calendar.set(Calendar.HOUR_OF_DAY, selectedHours)
                        calendar.set(Calendar.MINUTE,selectedMinute)
                        calendar.set(Calendar.SECOND,0)
                    }, 12, 0, false
                )
            timePickerDialog.show()
        })
        dateBtn.setOnClickListener(View.OnClickListener {
         val datePickerDialog=
             context?.let { it1 ->
                 DatePickerDialog(
                     it1, DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                         isSetDate=true
                         calendar.set(Calendar.YEAR,year)
                         calendar.set(Calendar.MONTH,month)
                         calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                     },year,month,day
                 )
             }
            datePickerDialog?.show()
        })
        eventBtn.setOnClickListener(View.OnClickListener {
            showEventAlert()
        })
        noti.setOnClickListener(View.OnClickListener {
            isNotification="Notification"
        })
        default.setOnClickListener(View.OnClickListener {
            isNotification="Default Sound"
        })
        one.setOnClickListener(View.OnClickListener {
            repeat=1
        })
        two.setOnClickListener(View.OnClickListener {
            repeat=2
        })
        three.setOnClickListener(View.OnClickListener {
            repeat=3
        })
        save.setOnClickListener(View.OnClickListener {
            if(getevent!=null&& isSetTime != false &&isSetDate!=false){
                if(repeat==1) {
                    eventSelected.getAlarmObject(
                        AlarmData(
                            true, calendar.timeInMillis+0.0, "not det", isNotification,
                            getevent!!

                        ), calendar
                    )
                    //   eventSelected.startAlarm(calendar)
                    dismiss()
                }else if (repeat==2){
                    eventSelected.getAlarmObject(
                        AlarmData(
                            true, calendar.timeInMillis+0.0, "not det", isNotification,
                            getevent!!

                        ), calendar
                    )
                    eventSelected.getAlarmObject(
                        AlarmData(
                            true, calendar.timeInMillis+(1.728e+8), "not det", isNotification,
                            getevent!!

                        ), calendar
                    )

                    dismiss()
                }
                else if(repeat==3){
                    eventSelected.getAlarmObject(
                        AlarmData(
                            true, calendar.timeInMillis+0.0, "not det", isNotification,
                            getevent!!

                        ), calendar
                    )
                    eventSelected.getAlarmObject(
                        AlarmData(
                            true, calendar.timeInMillis+(8.64e+7), "not det", isNotification,
                            getevent!!

                        ), calendar
                    )

                    eventSelected.getAlarmObject(
                        AlarmData(
                            true, calendar.timeInMillis+(1.728e+8), "not det", isNotification,
                            getevent!!

                        ), calendar
                    )

                    dismiss()
                }

            }else{
                Toast.makeText(context,"Required data ",Toast.LENGTH_SHORT).show()
            }
        })
        cancel.setOnClickListener(View.OnClickListener {
            dismiss()
        })

        return view
    }
    private fun showEventAlert() {
        /* var eventDialog=EventsCustomDialogFragment()
         activity?.supportFragmentManager?.let { eventDialog.show(it,"event") }*/
        var dialog=EventsCustomDialogFragment()
        dialog.setTargetFragment(this,1)
        fragmentManager?.let { dialog.show(it,"eventDialog") }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            eventSelected=targetFragment as EventsListener
        }catch (e: ClassCastException){
            Log.i("TAG", "onAttach: ${e.message}")
        }
    }

    public interface EventsListener  {
        fun getAlarmObject( alarmData: AlarmData,calendar: Calendar)
     //   fun startAlarm(calendar: Calendar)
    }

    override fun getEvent(event: String) {
        getevent=event
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setFullScreen()
        setWidthPercent(90)
    }
    /**
     * Call this method (in onActivityCreated or later) to set
     * the width of the dialog to a percentage of the current
     * screen width.
     */
    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    /**
     * Call this method (in onActivityCreated or later)
     * to make the dialog near-full screen.
     */
    fun DialogFragment.setFullScreen() {
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}