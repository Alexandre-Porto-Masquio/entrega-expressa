package com.apmasquio.entrega_expressa.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apmasquio.entrega_expressa.R
import com.apmasquio.entrega_expressa.data.AppDatabase
import com.apmasquio.entrega_expressa.databinding.ActivityDeliveryListBinding
import com.apmasquio.entrega_expressa.presentation.recyclerview.adapter.DeliveryListAdapter
import com.example.orgs.presentation.activity.DeliveryFormActivity

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
        adapter.atualiza(deliveryDao.buscaTodos())
    }

    private fun intentFab() {
        val fabAddDelivery = binding.fabAddDelivery
        fabAddDelivery.setOnClickListener {
            Toast.makeText(this, "apertou o buto", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DeliveryFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }
}