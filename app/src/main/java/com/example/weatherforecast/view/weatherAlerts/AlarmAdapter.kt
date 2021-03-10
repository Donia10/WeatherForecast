package com.example.weatherforecast.view.favouriteLoactions
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.model.local.AlarmData
import kotlinx.android.synthetic.main.alarm_item.view.*
import java.text.DateFormat
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class AlarmAdapter :ListAdapter <AlarmData, AlarmAdapter.AlarmViewHolder>(AlarmComparator()) {

    private lateinit var  context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val view=  LayoutInflater.from(parent.context).inflate(R.layout.alarm_item, parent, false)
        context=parent.context
        return AlarmViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val alarmData=getItem(position)
        holder.bind(alarmData)
        holder.itemView.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"clicked me $position",Toast.LENGTH_SHORT).show()
        })
    }

    class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val timeBtn : ImageButton? =itemView.time
        private val eventBtn :ImageButton ?=itemView.event
        private val deleteBtn: ImageButton? =itemView.delete_alarm

        private val time:TextView=itemView.txt_time
        private val alarmType:TextView=itemView.txt_alarmType
        private val event:TextView=itemView.txt_event

        fun bind(alarmData: AlarmData){
           val date=Date(alarmData.time)
            time.text=DateFormat.getTimeInstance(DateFormat.SHORT).format(date)
            event.text=alarmData.event
            alarmType.text=alarmData.alarmSound
        }


    }

    class AlarmComparator : DiffUtil.ItemCallback<AlarmData>(){
        override fun areItemsTheSame(oldItem: AlarmData, newItem: AlarmData): Boolean {
            return oldItem===newItem
        }

        override fun areContentsTheSame(oldItem: AlarmData, newItem: AlarmData): Boolean {
            return oldItem==newItem && oldItem ==newItem
        }

    }
}