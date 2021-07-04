package com.eng.androidweatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class WeatherIconCellAdapter(val weatherArray: ArrayList<JSONObject>) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.weather_icon_cell, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.findViewById<ImageView>(R.id.weatherIcon).setImageResource(DataGlobal.iconArray.find { weatherArray[position].getString("description").lowercase().replace(" ", "") == it.description.lowercase().replace(" ", "") }!!.icon)
        holder.view.findViewById<TextView>(R.id.weatherDescriptionTextView).text = (weatherArray[position]).getString("description").capitalize()
    }

    override fun getItemCount(): Int {
        return weatherArray.count()
    }
}