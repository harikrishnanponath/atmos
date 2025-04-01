package com.harikrish.atmos.model

import com.harikrish.atmos.api.forecast.Current
import com.harikrish.atmos.api.forecast.Forecast
import com.harikrish.atmos.api.forecast.Location

data class WeatherForecast(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)