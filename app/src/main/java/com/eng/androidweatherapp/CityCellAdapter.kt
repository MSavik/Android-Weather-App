package com.eng.androidweatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityCellAdapter(val cityNameList: ArrayList<String>, val cityCountryList: ArrayList<String>, val view: View) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.city_cell, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val cityText = holder.view.findViewById<TextView>(R.id.cityTextView)
        cityText.text = cityNameList[position] + "(" + cityCountryList[position] + ")"
        cityText.setOnClickListener {
            DataGlobal.citySelected = true
            DataGlobal.selectedCityName = cityNameList[position]
            DataGlobal.selectedCityCountry = cityCountryList[position]
            view.findViewById<TextView>(R.id.selectedCityTextView).text = DataGlobal.selectedCityName + "(" + DataGlobal.selectedCityCountry + ")"
        }
    }

    override fun getItemCount(): Int {
        return cityNameList.count()
    }
}