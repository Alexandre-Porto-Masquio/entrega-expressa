package com.apmasquio.entrega_expressa.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.apmasquio.entrega_expressa.data.api.UfApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeliveryFormViewModel : ViewModel() {

    val formData = MutableLiveData<MutableList<String>>()
    private val ufApi = UfApi.create()

    fun updateData(newData: ArrayList<String>) {
        formData.value = newData
    }

    fun getUfs() {
        formData.value = listOf("1", "2", "3").toMutableList()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val ufList = mutableListOf<String>()
                val ufApiResponse = ufApi.getUfs()
                for (uf in ufApiResponse) {
                    ufList.add(uf.sigla)
                }
                ufList.sort()
                formData.postValue(ufList)
            }
        }
    }
}