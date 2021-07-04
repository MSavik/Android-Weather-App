package com.eng.androidweatherapp

object DataGlobal {
    var iconArray: ArrayList<Weather> = arrayListOf()
    var citySelected = false
    var selectedCityName = ""
    var selectedCityCountry = ""
    var alertBackPressed = false

    fun setIcons() {
        iconArray.add(Weather("clear sky", R.drawable.i01d, false))
        iconArray.add(Weather("few clouds", R.drawable.i02d, false))
        iconArray.add(Weather("scattered clouds", R.drawable.i03d, false))
        iconArray.add(Weather("broken clouds", R.drawable.i04d, false))
        iconArray.add(Weather("shower rain", R.drawable.i09d, true))
        iconArray.add(Weather("rain", R.drawable.i10d, true))
        iconArray.add(Weather("thunderstorm", R.drawable.i11d, true))
        iconArray.add(Weather("snow", R.drawable.i13d, false))
        iconArray.add(Weather("mist", R.drawable.i50d, false))
        iconArray.add(Weather("thunderstorm with light rain", R.drawable.i11d, true))
        iconArray.add(Weather("thunderstorm with rain", R.drawable.i11d, true))
        iconArray.add(Weather("thunderstorm with heavy rain", R.drawable.i11d, true))
        iconArray.add(Weather("light thunderstorm", R.drawable.i11d, true))
        iconArray.add(Weather("heavy thunderstorm", R.drawable.i11d, true))
        iconArray.add(Weather("ragged thunderstorm", R.drawable.i11d, true))
        iconArray.add(Weather("thunderstorm with light drizzle", R.drawable.i11d, true))
        iconArray.add(Weather("thunderstorm with drizzle", R.drawable.i11d, true))
        iconArray.add(Weather("thunderstorm with heavy drizzle", R.drawable.i11d, true))
        iconArray.add(Weather("drizzle", R.drawable.i09d, true))
        iconArray.add(Weather("light intensity drizzle", R.drawable.i09d, true))
        iconArray.add(Weather("heavy intensity drizzle", R.drawable.i09d, true))
        iconArray.add(Weather("light intensity drizzle rain", R.drawable.i09d, true))
        iconArray.add(Weather("drizzle rain", R.drawable.i09d, true))
        iconArray.add(Weather("heavy intensity drizzle rain", R.drawable.i09d, true))
        iconArray.add(Weather("shower rain and drizzle", R.drawable.i09d, true))
        iconArray.add(Weather("heavy shower rain and drizzle", R.drawable.i09d, true))
        iconArray.add(Weather("shower drizzle", R.drawable.i09d, true))
        iconArray.add(Weather("light rain", R.drawable.i10d, false))
        iconArray.add(Weather("moderate rain", R.drawable.i10d, true))
        iconArray.add(Weather("heavy intensity rain", R.drawable.i10d, true))
        iconArray.add(Weather("very heavy rain", R.drawable.i10d, true))
        iconArray.add(Weather("extreme rain", R.drawable.i10d, true))
        iconArray.add(Weather("freezing rain", R.drawable.i13d, true))
        iconArray.add(Weather("light intensity shower rain", R.drawable.i09d, true))
        iconArray.add(Weather("shower rain", R.drawable.i09d, true))
        iconArray.add(Weather("heavy intensity shower rain", R.drawable.i09d, true))
        iconArray.add(Weather("ragged shower rain", R.drawable.i09d, true))
        iconArray.add(Weather("light snow", R.drawable.i13d, false))
        iconArray.add(Weather("heavy snow", R.drawable.i13d, true))
        iconArray.add(Weather("sleet", R.drawable.i13d, true))
        iconArray.add(Weather("light shower sleet", R.drawable.i13d, true))
        iconArray.add(Weather("shower sleet", R.drawable.i13d, true))
        iconArray.add(Weather("light rain and snow", R.drawable.i13d, false))
        iconArray.add(Weather("rain and snow", R.drawable.i13d, true))
        iconArray.add(Weather("light shower snow", R.drawable.i13d, false))
        iconArray.add(Weather("shower snow", R.drawable.i13d, true))
        iconArray.add(Weather("heavy shower snow", R.drawable.i13d, true))
        iconArray.add(Weather("smoke", R.drawable.i50d, true))
        iconArray.add(Weather("haze", R.drawable.i50d, true))
        iconArray.add(Weather("sand/ dust whirls", R.drawable.i50d, true))
        iconArray.add(Weather("fog", R.drawable.i50d, true))
        iconArray.add(Weather("sand", R.drawable.i50d, true))
        iconArray.add(Weather("dust", R.drawable.i50d, true))
        iconArray.add(Weather("volcanic ash", R.drawable.i50d, true))
        iconArray.add(Weather("squalls", R.drawable.i50d, true))
        iconArray.add(Weather("tornado", R.drawable.i50d, true))
        iconArray.add(Weather("few clouds: 11-25%", R.drawable.i02d, false))
        iconArray.add(Weather("scattered clouds: 25-50%", R.drawable.i03d, false))
        iconArray.add(Weather("broken clouds: 51-84%", R.drawable.i04d, false))
        iconArray.add(Weather("overcast clouds", R.drawable.i04d, false))
    }
}