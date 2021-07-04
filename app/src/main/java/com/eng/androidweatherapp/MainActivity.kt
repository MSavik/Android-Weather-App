package com.eng.androidweatherapp

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var weatherData: WeatherData
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!DataGlobal.citySelected) {
            startActivity(Intent(this, SelectCityActivity::class.java))
        }

        DataGlobal.setIcons()
        if (DataGlobal.selectedCityName == "")
            DataGlobal.selectedCityName = "Belgrade"

        val jsonWeatherURL = "https://api.openweathermap.org/data/2.5/forecast?q=${DataGlobal.selectedCityName.replace(" ", "%20")},${DataGlobal.selectedCityCountry}&appid=356292f8304f3c240d87417fc743ddb0"

        preuzmiWeatherDataJSON().execute(jsonWeatherURL)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        startActivity(Intent(this, SelectCityActivity::class.java))
    }

    fun setViews() {

        val adapter = WeatherCellAdapter(weatherData)
        val recyclerView = findViewById<RecyclerView>(R.id.weatherRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))

        findViewById<TextView>(R.id.cityTextView).text = weatherData.city.getString("name")
        findViewById<TextView>(R.id.countryTextView).text = "(" + weatherData.city.getString("country") + ")"
        findViewById<TextView>(R.id.timezoneTextView).text = "Timezone: " + weatherData.city.getString("timezone")
        findViewById<TextView>(R.id.latitudeTextView).text = "Lat: " + weatherData.city.getJSONObject("coord").getString("lat") + " / "
        findViewById<TextView>(R.id.longitudeTextView).text = "Lon: " + weatherData.city.getJSONObject("coord").getString("lon")
        findViewById<TextView>(R.id.populationTextView).text = "Population: " + weatherData.city.getString("population")

        val dateAllButton = findViewById<Button>(R.id.dateAllButton)
        val date1Button = findViewById<Button>(R.id.date1Button)
        val date2Button = findViewById<Button>(R.id.date2Button)
        val date3Button = findViewById<Button>(R.id.date3Button)
        val date4Button = findViewById<Button>(R.id.date4Button)
        val date5Button = findViewById<Button>(R.id.date5Button)
        val date6Button = findViewById<Button>(R.id.date6Button)

        dateAllButton.text = "Show all"
        date1Button.text = weatherData.list.getJSONObject(0).getString("dt_txt").removeRange(10, weatherData.list.getJSONObject(0).getString("dt_txt").length)
        date2Button.text = weatherData.list.getJSONObject(8).getString("dt_txt").removeRange(10, weatherData.list.getJSONObject(8).getString("dt_txt").length)
        date3Button.text = weatherData.list.getJSONObject(16).getString("dt_txt").removeRange(10, weatherData.list.getJSONObject(16).getString("dt_txt").length)
        date4Button.text = weatherData.list.getJSONObject(24).getString("dt_txt").removeRange(10, weatherData.list.getJSONObject(24).getString("dt_txt").length)
        date5Button.text = weatherData.list.getJSONObject(32).getString("dt_txt").removeRange(10, weatherData.list.getJSONObject(24).getString("dt_txt").length)
        date6Button.text = weatherData.list.getJSONObject(weatherData.list.length()-1).getString("dt_txt").removeRange(10, weatherData.list.getJSONObject(weatherData.list.length()-1).getString("dt_txt").length)

        dateAllButton.setOnClickListener {
            recyclerView.adapter = adapter
            recyclerView.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        }
        date1Button.setOnClickListener {
            filterWeatherData(weatherData.list.getJSONObject(0).getString("dt_txt"))
        }
        date2Button.setOnClickListener {
            filterWeatherData(weatherData.list.getJSONObject(8).getString("dt_txt"))
        }
        date3Button.setOnClickListener {
            filterWeatherData(weatherData.list.getJSONObject(16).getString("dt_txt"))
        }
        date4Button.setOnClickListener {
            filterWeatherData(weatherData.list.getJSONObject(24).getString("dt_txt"))
        }
        date5Button.setOnClickListener {
            filterWeatherData(weatherData.list.getJSONObject(32).getString("dt_txt"))
        }
        date6Button.setOnClickListener {
            filterWeatherData(weatherData.list.getJSONObject(weatherData.list.length()-1).getString("dt_txt"))
        }

        if (weatherData.list.getJSONObject(32).getString("dt_txt") == weatherData.list.getJSONObject(weatherData.list.length()-1).getString("dt_txt")) {
            date6Button.visibility = View.GONE
        }
    }

    fun filterWeatherData(date: String) {
//        var filteredWeatherData = weatherData
        var filteredWeatherData = WeatherData(weatherData.cod, weatherData.message, weatherData.cnt, weatherData.list, weatherData.city)
        filteredWeatherData.list = JSONArray(arrayListOf<String>())
        val jsonArray = weatherData.list
        println(jsonArray)
        jsonArray.let {
            (0 until it.length()).forEach {
                val jsonObject = jsonArray.getJSONObject(it)
                if (jsonObject.getString("dt_txt").removeRange(10, 19) == date.removeRange(10, 19)) {
                    filteredWeatherData.list.put(jsonObject)
                }
            }
        }
        println(filteredWeatherData)
        val adapter = WeatherCellAdapter(filteredWeatherData)
        val recyclerView = findViewById<RecyclerView>(R.id.weatherRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
    }

    inner class preuzmiWeatherDataJSON : AsyncTask<String, String, String>() {
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
            val jsonObject = JSONObject(result!!)
            weatherData = WeatherData(jsonObject.getInt("cod"), jsonObject.getInt("message"), jsonObject.getInt("cnt"), jsonObject.getJSONArray("list"), jsonObject.getJSONObject("city"))

            var isBadWeather = false

            if (DataGlobal.selectedCityName != "") {
                weatherData.list.let {
                    (0 until weatherData.list.length()).forEach {

                        val jsonObject = weatherData.list.getJSONObject(it)
                        for (icon in DataGlobal.iconArray) {
                            val iconDescription = icon.description.lowercase().replace(" ", "")
                            val jsonObjectDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description").lowercase().replace(" ", "")
                            if (iconDescription == jsonObjectDescription) {
                                if (icon.badWeather && !DataGlobal.alertBackPressed) {
                                    isBadWeather = true
                                }
                            }
                        }
                    }
                }
            }
            if (isBadWeather) {
                startActivity(Intent(context, AlertActivity::class.java))
            } else {
                setContentView(R.layout.activity_main)
                setViews()
            }
        }
    }
}

data class Weather(val description: String, val icon: Int, val badWeather: Boolean)

data class WeatherData(
    var cod: Int,
    var message: Int,
    var cnt: Int,
    var list: JSONArray,
    var city: JSONObject
)