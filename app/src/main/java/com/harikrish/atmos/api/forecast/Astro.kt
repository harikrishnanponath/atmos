package com.harikrish.atmos.api.forecast

data class Astro(
    val is_moon_up: String,
    val is_sun_up: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
)