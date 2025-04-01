package com.harikrish.atmos.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.harikrish.atmos.R
import com.harikrish.atmos.api.NetworkResponse
import com.harikrish.atmos.api.forecast.Hour
import com.harikrish.atmos.model.WeatherForecast
import com.harikrish.atmos.viewmodel.WeatherViewModel
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny


@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    var city by remember { mutableStateOf("") }
    val weatherResult = viewModel.weatherResult.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)

    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.atmosbackground),
            contentDescription = "Weather Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = city,
                    singleLine = true,
                    textStyle = TextStyle(color = Color.White),
                    onValueChange = { city = it },
                    label = { Text("Enter City", color = Color.White) }
                )
                IconButton(onClick = { viewModel.getData(city) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search for any location",
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            when (val result = weatherResult.value) {
                is NetworkResponse.Error -> Text(text = result.error, color = Color.Red)
                NetworkResponse.Loading -> CircularProgressIndicator()
                is NetworkResponse.Success -> WeatherDetails(result.data)
                null -> {}
            }
        }
    }
}

@Composable
fun WeatherDetails(weather: WeatherForecast) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(topEnd = 50.dp, bottomStart = 50.dp))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF0A3760), Color(0xFF1565C0)),
                        start = Offset(0f, 500f),
                        end = Offset(500f, 0f)
                    )
                ),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = weather.location.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "${weather.current.temp_c}°",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = weather.current.condition.text,
                    fontSize = 18.sp,
                    color = Color(0xFFE1BEE7)
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                InfoItem(icon = Icons.Default.Air, label = "${weather.current.wind_kph} km/h")
                InfoItem(icon = Icons.Default.WaterDrop, label = "${weather.current.humidity}%")
                InfoItem(
                    icon = Icons.Default.WbSunny,
                    label = "Sunrise: ${weather.forecast.forecastday[0].astro.sunrise}"
                )
            }
        }

        AsyncImage(
            model = "https:${weather.current.condition.icon}".replace("64x64", "128x128"),
            contentDescription = "Weather Icon",
            modifier = Modifier
                .size(180.dp)  // Set the size of the image
                .align(Alignment.TopEnd)  // Align to the top-right of the Box
        )

    }

    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Hourly Forecast",
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp)
    )
    Spacer(modifier = Modifier.size(32.dp))
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
        items(weather.forecast.forecastday[0].hour?.take(25) ?: emptyList()) { hour ->
            HourlyForecastItem(hour = hour)
        }
    }
}

@Composable
fun InfoItem(icon: ImageVector, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, color = Color.White, fontSize = 14.sp)
    }
}

@Composable
fun HourlyForecastItem(hour: Hour) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(150.dp)  // Set a specific height for the card
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF0A3760), Color(0xFF1565C0)),
                    start = Offset(0f, 250f),
                    end = Offset(250f, 0f)
                )
            ),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,  // Align items in the center vertically
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = "${hour.time.substringAfter(" ")}",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)  // Center the text
            )
            AsyncImage(
                model = "https:${hour.condition.icon}".replace("64x64", "128x128"),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = "${hour.temp_c}°",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)  // Center the text
            )
        }
    }

}


@Preview
@Composable
fun WeatherScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
//                WeatherScreen()
    }

}