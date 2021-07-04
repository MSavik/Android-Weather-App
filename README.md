# Android Weather App

## About
An Android weather app that displays weather predictions for every city in the world for the next 5 days.  
The app was written using Kotlin.

## Description

### How it works

The app consists of three activities:
* Select City Activity
* Main Activity (this is where the weather data for a particular city is displayed)
* Alert Activity

The Select City Activity allows users to select a specific city for which the weather data will be displayed.  
Since the list is of cities is too long to be scrolled through, a dynamic search bar is provided at the top of the page.  
Once the city is selected, the name of the currently selected city will be displayed above the "SELECT CITY" button.  
Once the button is clicked, the weather data for that city will be displayed in the Main Activity. If there is any bad weather for that city during the next 5 days, the Alert Activity will start first to inform the user that there will be a bad weather.

The Main Activity contains information about the city at the top of the page, information about the weather withing the next 5 days within a scrollable layout in the middle of the page, and filters at the bottom of the page for filtering weather data for a specific day.

The Alert Activity will only be triggered after clicking the "SELECT CITY" button if there is any bad weather for that city during the next 5 days. The system "Back" button will redirect the user to the Main Activity.

## Data sources used

* [raw.githubusercontent.com/lutangar/cities.json/master/cities.json](https://raw.githubusercontent.com/lutangar/cities.json/master/cities.json)
* [openweathermap.org/api](https://openweathermap.org/api)
