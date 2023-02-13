package com.apmasquio.entrega_expressa.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.apmasquio.entrega_expressa.R
import com.apmasquio.entrega_expressa.data.AppDatabase
import com.apmasquio.entrega_expressa.databinding.ActivityDeliveryListBinding
import com.apmasquio.entrega_expressa.presentation.adapter.DeliveryListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeliveryListActivity : AppCompatActivity(R.layout.activity_delivery_list) {

    val adapter = DeliveryListAdapter()

    private lateinit var binding: ActivityDeliveryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Lista de Entregas"
        binding = ActivityDeliveryListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        configureRecyclerView()
        intentFab()
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.dbInstance(this)
        val deliveryDao = db.deliveryDao()
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                adapter.atualiza(deliveryDao.getAll())
            }
        }
        adapter.notifyData()
    }

    private fun intentFab() {
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
}