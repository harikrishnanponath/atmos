package com.harikrish.atmos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harikrish.atmos.api.Constant
import com.harikrish.atmos.api.NetworkResponse
import com.harikrish.atmos.api.RetrofitInstance
import com.harikrish.atmos.model.WeatherForecast
import kotlinx.coroutines.launch

class WeatherViewModel :ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherForecast>>()
    val weatherResult : LiveData<NetworkResponse<WeatherForecast>> = _weatherResult

init {
    getData(city = "london, ontario")
}

    fun getData(city : String){
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try{
                val response = weatherApi.getWeather(Constant.API_KEY,city, "7")
                if(response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            }
            catch (e : Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }

        }
    }

}
