package com.harikrish.atmos.api.forecast

data class Current(
    val cloud: String,
    val condition: Condition,
    val dewpoString_f: String,
    val feelslike_c: String,
    val feelslike_f: String,
    val heatindex_f: String,
    val humidity: String,
    val is_day: String,
    val last_updated: String,
    val last_updated_epoch: String,
    val precip_in: String,
    val precip_mm: String,
    val pressure_in: String,
    val pressure_mb: String,
    val temp_c: String,
    val temp_f: String,
    val wind_degree: String,
    val wind_dir: String,
    val wind_kph: String,
    val wind_mph: String
)