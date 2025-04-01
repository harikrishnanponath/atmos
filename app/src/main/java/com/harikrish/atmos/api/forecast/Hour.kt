package com.harikrish.atmos.api.forecast

data class Hour(
    val condition: ConditionX,
    val is_day: String,
    val temp_c: String,
    val temp_f: String,
    val time: String,
    val time_epoch: String
)