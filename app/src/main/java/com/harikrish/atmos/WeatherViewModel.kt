package com.harikrish.atmos

import android.util.Log
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {

    fun getWeather(city : String) {
        Log.i("CITY", city)
    }
}