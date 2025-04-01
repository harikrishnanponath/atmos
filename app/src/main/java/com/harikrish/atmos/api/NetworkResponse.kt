package com.harikrish.atmos.api

sealed class NetworkResponse<out T> {

    data class Success<out T>(val data: T): NetworkResponse<T>()
    data class Error(val error: String): NetworkResponse<Nothing>()
    data object Loading : NetworkResponse<Nothing>()
}