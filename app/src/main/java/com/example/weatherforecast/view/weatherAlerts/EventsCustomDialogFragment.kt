package com.example.weatherforecast.view.weatherAlerts

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import com.example.weatherforecast.R
import kotlinx.android.synthetic.main.custom_dialog_fragment_events.view.*
import java.lang.ClassCastException
import java.util.*

class EventsCustomDialogFragment: DialogFragment() {
    private  var listener: EventsListener? =null


    private lateinit var eventSelected:EventsListener
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View =inflater.inflate(R.layout.custom_dialog_fragment_events, container, false)

        val rainBtn:Button= view.rain
        rainBtn.setOnClickListener(View.OnClickListener {
            eventSelected.getEvent(rainBtn.text.toString())
            dismiss()
        })
        val tempBtn:Button= view.temperature
        tempBtn.setOnClickListener(View.OnClickListener {
            eventSelected.getEvent(tempBtn.text.toString())
            dismiss()
        })
        val windBtn:Button= view.wind
        windBtn.setOnClickListener(View.OnClickListener {
            eventSelected.getEvent(windBtn.text.toString())
            dismiss()
        })
        val fogBtn:Button= view.fog_mist_haze
        fogBtn.setOnClickListener(View.OnClickListener {
            eventSelected.getEvent(fogBtn.text.toString())
            dismiss()
        })
        val snowBtn:Button= view.snow
        snowBtn.setOnClickListener(View.OnClickListener {
            eventSelected.getEvent(snowBtn.text.toString())
            dismiss()
        })
        val cloudBtn:Button= view.cloud
        cloudBtn.setOnClickListener(View.OnClickListener {
            eventSelected.getEvent(cloudBtn.text.toString())
            dismiss()
        })
        val thundBtn:Button= view.thunderstorm
        thundBtn.setOnClickListener(View.OnClickListener {
            eventSelected.getEvent(thundBtn.text.toString())
            dismiss()
        })
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            eventSelected=targetFragment as EventsListener
        }catch (e:ClassCastException){
            Log.i("TAG", "onAttach: ${e.message}")
        }
    }

    public interface EventsListener  {
        fun getEvent( event: String)
    }
}