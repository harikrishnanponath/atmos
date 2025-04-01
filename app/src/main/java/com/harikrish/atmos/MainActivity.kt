package com.harikrish.atmos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.harikrish.atmos.ui.WeatherScreen
import com.harikrish.atmos.ui.theme.AtmosTheme
import com.harikrish.atmos.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        setContent {
            AtmosTheme {
                WeatherScreen(weatherViewModel)
            }

        }
    }
}
