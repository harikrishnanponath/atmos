package com.harikrish.atmos.api.forecast

data class Day(
    val avgtemp_c: String,
    val avgtemp_f: String,
    val daily_chance_of_rain: String,
    val daily_will_it_rain: String,
    val daily_will_it_snow: String,
    val maxtemp_c: String,
    val maxtemp_f: String,
    val mStringemp_c: String,
    val mStringemp_f: String,
    val totalsnow_cm: String
)