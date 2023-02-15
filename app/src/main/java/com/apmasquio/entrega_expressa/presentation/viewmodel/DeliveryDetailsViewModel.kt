package com.apmasquio.entrega_expressa.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.apmasquio.entrega_expressa.data.AppDatabase
import com.apmasquio.entrega_expressa.data.dao.DeliveryDao
import com.apmasquio.entrega_expressa.data.models.Delivery
import com.apmasquio.entrega_expressa.presentation.adapter.DeliveryListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeliveryDetailsViewModel : ViewModel() {

    private lateinit var deliveryDao: DeliveryDao
    val detailsData = MutableLiveData<List<Delivery>>()

    fun initializeDeliveryDao(thisContext: Context) {
        deliveryDao = AppDatabase.dbInstance(thisContext).deliveryDao()
    }

    fun delete(thisContext: Context, item: Delivery) {
        initializeDeliveryDao(thisContext)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deliveryDao.delete(item)
            }
        }
    }

}