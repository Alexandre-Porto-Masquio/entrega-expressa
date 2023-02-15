package com.apmasquio.entrega_expressa.presentation.deliverylist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.apmasquio.entrega_expressa.R
import com.apmasquio.entrega_expressa.databinding.ActivityDeliveryListBinding
import com.apmasquio.entrega_expressa.presentation.deliveryform.DeliveryFormActivity
import com.apmasquio.entrega_expressa.presentation.adapter.DeliveryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeliveryListActivity : AppCompatActivity(R.layout.activity_delivery_list) {

    private val listViewModel: DeliveryListViewModel by viewModels()
    private val adapter = DeliveryListAdapter()

    private lateinit var binding: ActivityDeliveryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Lista de Entregas"
        binding = ActivityDeliveryListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        observeViewModel()
        configureRecyclerView()
        setupIntentFab()
    }

    override fun onResume() {
        super.onResume()
            listViewModel.getAll()
    }

    private fun setupIntentFab() {
        val fabAddDelivery = binding.fabAddDelivery
        fabAddDelivery.setOnClickListener {
            val intent = Intent(this, DeliveryFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        listViewModel.listData.observe(this) {
            adapter.update(it)
            adapter.notifyData()
        }
    }
}