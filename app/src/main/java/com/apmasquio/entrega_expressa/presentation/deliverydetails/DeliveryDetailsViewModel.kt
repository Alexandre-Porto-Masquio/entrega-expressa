package com.apmasquio.entrega_expressa.presentation.deliverydetails

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
class DeliveryDetailsViewModel @Inject constructor(
    private val deliveryDao: DeliveryDao
) : ViewModel() {

    val deliveryDetailsData = MutableLiveData<List<Delivery>>()

    fun delete(item: Delivery) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deliveryDao.delete(item)
            }
        }
    }

    fun loadDelivery() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deliveryDetailsData.postValue(deliveryDao.getAll())
            }
        }
    }
}