package com.eng.androidweatherapp

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class SelectCityActivity : AppCompatActivity() {

    var cityNameList: ArrayList<String> = arrayListOf()
    var cityCountryList: ArrayList<String> = arrayListOf()
    var context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_city)

        DataGlobal.alertBackPressed = false

        val jsonCityURL = "https://raw.githubusercontent.com/lutangar/cities.json/master/cities.json"

        preuzmiCityJSON().execute(jsonCityURL)

        findViewById<Button>(R.id.selectCityButton).setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }

        if (DataGlobal.citySelected) {
            findViewById<TextView>(R.id.selectedCityTextView).text = DataGlobal.selectedCityName + "(" + DataGlobal.selectedCityCountry + ")"
        }

        findViewById<EditText>(R.id.cityDynamicSearchBar).addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                search()
            }

        })
    }

    fun search() {
        val recyclerView = findViewById<RecyclerView>(R.id.cityRecyclerView)
        var filteredCityNames: ArrayList<String> = arrayListOf()
        val filteredCityCountries: ArrayList<String> = arrayListOf()
        val searchBar = findViewById<EditText>(R.id.cityDynamicSearchBar)

        if (cityNameList.filter { searchBar.text.toString().lowercase().replace(" ", "") in it.lowercase().replace(" ", "")}.isNotEmpty()) {
            filteredCityNames.clear()
            var cityIndex = 0
            for (cityName in cityNameList) {
                if (searchBar.text.toString().lowercase().replace(" ", "") in cityName.lowercase().replace(" ", "")) {
                    filteredCityNames.add(cityName)
                    filteredCityCountries.add(cityCountryList[cityIndex])
                }
                cityIndex++
            }
        } else {
            filteredCityNames = cityNameList
        }
        recyclerView.adapter = CityCellAdapter(filteredCityNames, filteredCityCountries, findViewById(R.id.selectCityLayout))
        recyclerView.setLayoutManager(LinearLayoutManager(parent, LinearLayoutManager.VERTICAL, false))
    }

    inner class preuzmiCityJSON : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {
            var json: String
            val connection = URL(params[0]).openConnection() as HttpURLConnection

            try {
                connection.connect()
                json = connection.inputStream.use {
                    it.reader().use {
                            reader -> reader.readText()
                    }
                }
            } finally {
                connection.disconnect()
            }
            return json
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val jsonArray = JSONArray(result)

            jsonArray.let {
                (0 until jsonArray.length()).forEach {
                    cityNameList.add(jsonArray.getJSONObject(it).getString("name"))
                    cityCountryList.add(jsonArray.getJSONObject(it).getString("country"))
                }
            }

            val adapter = CityCellAdapter(cityNameList, cityCountryList, findViewById(R.id.selectCityLayout))
            val recyclerView = findViewById<RecyclerView>(R.id.cityRecyclerView)
            recyclerView.adapter = adapter
            recyclerView.setLayoutManager(LinearLayoutManager(parent, LinearLayoutManager.VERTICAL, false))
        }
    }
}