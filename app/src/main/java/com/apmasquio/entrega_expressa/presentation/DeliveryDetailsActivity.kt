package com.apmasquio.entrega_expressa.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apmasquio.entrega_expressa.R
import com.apmasquio.entrega_expressa.data.AppDatabase
import com.apmasquio.entrega_expressa.data.models.Delivery
import com.apmasquio.entrega_expressa.databinding.ActivityDeliveryDetailsBinding
import com.apmasquio.entrega_expressa.presentation.viewmodel.DeliveryDetailsViewModel
import com.apmasquio.entrega_expressa.presentation.viewmodel.DeliveryListViewModel
import com.apmasquio.entrega_expressa.utils.Constants.DELIVERY_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeliveryDetailsActivity : AppCompatActivity(R.layout.activity_delivery_details) {
    private lateinit var detailsViewModel: DeliveryDetailsViewModel
    private var delivery: Delivery? = null
    private val binding by lazy {
        ActivityDeliveryDetailsBinding.inflate(layoutInflater)
    }
    private val deliveryDao by lazy {
        AppDatabase.dbInstance(this).deliveryDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Detalhes da Entrega"

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        tryLoadDelivery()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_delivery_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_details_remove -> {
                delivery?.let {
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            deliveryDao.delete(it)
                        }
                    }
                }
                finish()
            }

            R.id.menu_detais_edit -> {
                Intent(this, DeliveryFormActivity::class.java).apply {
                    putExtra(DELIVERY_KEY, delivery)
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tryLoadDelivery() {
        val db = AppDatabase.dbInstance(this)
        val deliveryDao = db.deliveryDao()
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val deliveries = deliveryDao.getAll()
                intent.getIntExtra(DELIVERY_KEY,-1).let { position ->
                    delivery = deliveries[position]
                    fillFields(delivery!!)
                }
            }
        }
    }

    private fun fillFields(deliveryCarregado: Delivery) {
        binding.nameDetailDelivery.setText(deliveryCarregado.name)
        binding.quantityDetailDelivery.setText(deliveryCarregado.quantity)
        binding.dateDetailDelivery.setText(deliveryCarregado.date)
        binding.clientDetailDelivery.setText(deliveryCarregado.client)
        binding.cpfDetailDelivery.setText(deliveryCarregado.cpf)
        binding.cepDetailDelivery.setText(deliveryCarregado.cep)
        binding.ufDetailDelivery.setText(deliveryCarregado.uf)
        binding.cityDetailDelivery.setText(deliveryCarregado.city)
        binding.neighborhoodDetailDelivery.setText(deliveryCarregado.neighborhood)
        binding.streetDetailDelivery.setText(deliveryCarregado.street)
        binding.numberDetailDelivery.setText(deliveryCarregado.number)
        binding.complementDetailDelivery.setText(deliveryCarregado.complement)

    }

    private fun configureViewModel() {
        detailsViewModel = ViewModelProvider(this)[DeliveryDetailsViewModel::class.java]

        detailsViewModel.detailsData.observe(this) {
            //run code
        }
    }

}