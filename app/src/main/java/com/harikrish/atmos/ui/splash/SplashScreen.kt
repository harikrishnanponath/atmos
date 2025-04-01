package com.harikrish.atmos.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.harikrish.atmos.R
import kotlinx.coroutines.delay



@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000) // Show splash screen for 2 seconds
        navController.navigate("main") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.atmosbackground), // Your background image
            contentDescription = "Splash Screen Background",
            modifier = Modifier.fillMaxSize(), // Makes the image cover the entire screen
            contentScale = ContentScale.Crop // This will crop and fill the screen without distortion
        )

        // Logo in the center
        Image(
            painter = painterResource(id = R.drawable.atmosnoback), // Your logo
            contentDescription = "Atmos Logo",
            modifier = Modifier.size(250.dp).align(Alignment.Center),

        )
    }
}
