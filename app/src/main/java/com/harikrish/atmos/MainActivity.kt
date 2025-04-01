package com.harikrish.atmos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.harikrish.atmos.ui.WeatherScreen
import com.harikrish.atmos.ui.splash.SplashScreen
import com.harikrish.atmos.ui.theme.AtmosTheme
import com.harikrish.atmos.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        setContent {
            AtmosTheme {
                SetupNavigation(weatherViewModel)
            }
        }
    }
}
@Composable
fun SetupNavigation(weatherViewModel: WeatherViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("main") { WeatherScreen(weatherViewModel) } // Main screen after the splash
    }
}

