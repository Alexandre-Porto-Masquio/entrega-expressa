package com.apmasquio.entrega_expressa.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apmasquio.entrega_expressa.data.api.LocationApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeliveryFormViewModel : ViewModel() {

    val ufListFormData = MutableLiveData<MutableList<String>>()
    val cityListFormData = MutableLiveData<MutableList<String>>()
    private val locationApi = LocationApi.create()

    fun getUfs() {
        ufListFormData.value = listOf("").toMutableList()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val ufList = mutableListOf<String>()
                val ufApiResponse = locationApi.getUfs()
                for (uf in ufApiResponse) {
                    ufList.add(uf.sigla)
                }
                ufList.sort()
                ufListFormData.postValue(ufList)
            }
        }
    }
    fun getCities(uf : String) {
        cityListFormData.value = listOf("1", "2", "3").toMutableList()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val cityList = mutableListOf<String>()
                val cityApiResponse = locationApi.getCities(uf)
                for (city in cityApiResponse) {
                    cityList.add(city.nome)
                }
                cityList.sort()
                cityListFormData.postValue(cityList)
            }
        }
    }
}