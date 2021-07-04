package com.eng.androidweatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class WeatherCellAdapter(val weatherData: WeatherData) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.weather_cell, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.tempTextView).text = weatherData.list.getJSONObject(position).getJSONObject("main").getDouble("temp").toString() + " K"
        holder.view.findViewById<TextView>(R.id.pressureTextView).text = "Pressure: " + weatherData.list.getJSONObject(position).getJSONObject("main").getDouble("pressure").toString() + " mbar"
        holder.view.findViewById<TextView>(R.id.humidityTextView).text = "Humidity: " + weatherData.list.getJSONObject(position).getJSONObject("main").getDouble("humidity").toString() + " mbar"
        holder.view.findViewById<TextView>(R.id.seaLevelTextView).text = "Sea level: " + weatherData.list.getJSONObject(position).getJSONObject("main").getDouble("sea_level").toString() + " mbar"
        holder.view.findViewById<TextView>(R.id.groundLevelTextView).text = "Ground level: " + weatherData.list.getJSONObject(position).getJSONObject("main").getDouble("grnd_level").toString() + " mbar"
        holder.view.findViewById<TextView>(R.id.visibilityTextView).text = "Visibility: " + weatherData.list.getJSONObject(position).getInt("visibility").toString() + " mi"
        holder.view.findViewById<TextView>(R.id.windSpeedTextView).text = "Wind speed: " + weatherData.list.getJSONObject(position).getJSONObject("wind").getDouble("speed") + " m/s"
        holder.view.findViewById<TextView>(R.id.windDegTextView).text = "Wind direction: " + weatherData.list.getJSONObject(position).getJSONObject("wind").getDouble("deg") + " \u00b0"
        holder.view.findViewById<TextView>(R.id.windGustTextView).text = "Wind gust: " + weatherData.list.getJSONObject(position).getJSONObject("wind").getDouble("gust") + " m/s"
        holder.view.findViewById<TextView>(R.id.cloudsTextView).text = "Clouds: " + weatherData.list.getJSONObject(position).getJSONObject("clouds").getDouble("all") + " %"
        holder.view.findViewById<TextView>(R.id.dateAndTimeTextView).text = weatherData.list.getJSONObject(position).getString("dt_txt")

        var weatherDataList: ArrayList<JSONObject> = arrayListOf()
        weatherData.list.getJSONObject(position).getJSONArray("weather")?.let {
            (0 until it.length()).forEach {
                weatherDataList.add(weatherData.list.getJSONObject(position).getJSONArray("weather").getJSONObject(it))
            }
        }

        val adapter = WeatherIconCellAdapter(weatherDataList)
        val recyclerView = holder.view.findViewById<RecyclerView>(R.id.weatherIconRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.setLayoutManager(LinearLayoutManager(holder.view.context, LinearLayoutManager.HORIZONTAL, false))
    }

    override fun getItemCount(): Int {
        return weatherData.list.length()
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view)