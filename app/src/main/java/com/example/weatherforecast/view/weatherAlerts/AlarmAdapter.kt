package com.example.weatherforecast.view.weatherAlerts
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.model.local.AlarmData
import kotlinx.android.synthetic.main.alarm_item.view.*
import java.text.DateFormat
import java.util.*

class AlarmAdapter :ListAdapter <AlarmData, AlarmAdapter.AlarmViewHolder>(AlarmComparator()) {

    private lateinit var context:Context

    lateinit var listener:OnItemClickListener

    interface OnItemClickListener{
        fun deleteFromApter(position: Int)
        fun removeFromRoom(position:Int)
        fun removeAlarm(alarmData: AlarmData)
    }

    fun setOnClickListener( listenerr: OnItemClickListener){listener=listenerr}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val view=  LayoutInflater.from(parent.context).inflate(R.layout.alarm_item, parent, false)
        context=parent.context
        return AlarmViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {

        val alarmData=getItem(position)
        holder.bind(alarmData)


    }

    class AlarmViewHolder(itemView: View, val listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {

        private val timeBtn : ImageButton? =itemView.time
        private val eventBtn :ImageButton ?=itemView.event
        val deleteBtn: ImageButton? =itemView.delete_alarm

        private val time:TextView=itemView.txt_time
        private val alarmType:TextView=itemView.txt_alarmType
        private val event:TextView=itemView.txt_event
        fun bind(alarmData: AlarmData) {
            val date = Date(alarmData.time)
            val currentTime = Calendar.getInstance().time

            if(date <= currentTime){
                listener.removeAlarm(alarmData)
            }
            time.text = DateFormat.getTimeInstance(DateFormat.SHORT).format(date)
            event.text = alarmData.event
            alarmType.text = alarmData.alarmSound


            deleteBtn?.setOnClickListener(View.OnClickListener {
                if(listener!=null) {
                    val position: Int = adapterPosition
                    if (position!=RecyclerView.NO_POSITION) {
                        listener.deleteFromApter(position)
                        listener.removeFromRoom(position)

                    }
                }
            })
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