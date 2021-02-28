package com.example.weatherforecast

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforecast.viewmodel.WeatherHomeViewModel
import org.w3c.dom.Text

class Home : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        // Inflate the layout for this fragment
       val view:View= inflater.inflate(R.layout.fragment_home, container, false)

        val textView:TextView=view.findViewById(R.id.home)
        textView.text="000"
        Log.i("TAG", "home: ")

        val viewModel = ViewModelProvider(this).get(WeatherHomeViewModel::class.java)
        viewModel.fetchData()
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { data ->data?.let {
            textView.text=data.timezone+data.current+data.daily+data.current+data.lat+data.lon+data.minutely+data.timezoneOffset
        } })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }





}