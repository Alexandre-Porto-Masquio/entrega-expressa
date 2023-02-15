package com.apmasquio.entrega_expressa.presentation.deliverylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apmasquio.entrega_expressa.data.dao.DeliveryDao
import com.apmasquio.entrega_expressa.data.models.Delivery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DeliveryListViewModel @Inject constructor(
    private val deliveryDao: DeliveryDao
) : ViewModel() {

    val listData = MutableLiveData<List<Delivery>>()

    fun getAll() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                listData.postValue(deliveryDao.getAll())
            }
        }
    }
}