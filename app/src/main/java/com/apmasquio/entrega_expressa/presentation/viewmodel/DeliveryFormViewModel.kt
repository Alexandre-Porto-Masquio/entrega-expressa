package com.apmasquio.entrega_expressa.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apmasquio.entrega_expressa.data.api.LocationApi
import com.apmasquio.entrega_expressa.data.dao.DeliveryDao
import com.apmasquio.entrega_expressa.data.models.Delivery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DeliveryFormViewModel @Inject constructor(
    private val locationApi: LocationApi,
    private val deliveryDao: DeliveryDao
) : ViewModel() {

    val ufListFormData = MutableLiveData<MutableList<String>>()
    val cityListFormData = MutableLiveData<MutableList<String>>()
    val finishFormData = MutableLiveData<Boolean>()


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

    fun getCities(uf: String) {
        cityListFormData.value = listOf(" ").toMutableList()
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

    fun saveOrUpdate(
        delivery: Delivery,
        update: Boolean
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (update) {
                    deliveryDao.update(delivery)
                } else {
                    deliveryDao.save(delivery)
                }
                finishFormData.postValue(true)
            }
        }
    }
}