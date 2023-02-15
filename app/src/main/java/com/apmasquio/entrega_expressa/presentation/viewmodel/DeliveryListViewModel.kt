package com.apmasquio.entrega_expressa.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.apmasquio.entrega_expressa.data.AppDatabase
import com.apmasquio.entrega_expressa.data.models.Delivery
import com.apmasquio.entrega_expressa.presentation.adapter.DeliveryListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeliveryListViewModel : ViewModel() {

    val listData = MutableLiveData<List<Delivery>>()

    fun getAll(thisContext: Context) {
        val db = AppDatabase.dbInstance(thisContext)
        val deliveryDao = db.deliveryDao()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                listData.postValue(deliveryDao.getAll())
            }
        }
    }
}