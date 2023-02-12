package com.apmasquio.entrega_expressa.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apmasquio.entrega_expressa.R
import com.apmasquio.entrega_expressa.data.AppDatabase
import com.apmasquio.entrega_expressa.data.models.Delivery
import com.apmasquio.entrega_expressa.databinding.ActivityDeliveryDetailsBinding
import com.apmasquio.entrega_expressa.utils.Constants.DELIVERY_KEY

class DeliveryDetailsActivity : AppCompatActivity(R.layout.activity_delivery_details) {

    private var deliveryId: Long = 0
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
        tryLoadDelivery()
    }

    override fun onResume() {
        super.onResume()
        deliveryId.let { id ->
            delivery = deliveryDao.searchById(id)
        }
        delivery?.let {
            fillFields(it)
        } ?: finish()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_delivery_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_details_remove -> {
                Toast.makeText(
                    this, "clicou no menu remover", Toast.LENGTH_SHORT
                ).show()
                delivery?.let {
                    deliveryDao.delete(it)
                }
                finish()
            }

            R.id.menu_detais_edit -> {
                Toast.makeText(
                    this, "clicou no menu editar", Toast.LENGTH_SHORT
                ).show()
                Intent(this, DeliveryFormActivity::class.java).apply {
                    putExtra(DELIVERY_KEY, delivery)
                    startActivity(this)

                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tryLoadDelivery() {

        intent.getParcelableExtra<Delivery>(DELIVERY_KEY)?.let { produtoCarregado ->
            delivery = produtoCarregado
            deliveryId = produtoCarregado.id
        } ?: finish()
    }

    private fun fillFields(deliveryCarregado: Delivery) {
        binding.nameDetailDelivery.setText("Nome: " + deliveryCarregado.name)
        binding.quantityDetailDelivery.setText(deliveryCarregado.quantity)
        binding.dateDetailDelivery.setText(deliveryCarregado.date)
        binding.clientDetailDelivery.setText(deliveryCarregado.client)
        binding.cpfDetailDelivery.setText(deliveryCarregado.cpf)
        binding.cepDetailDelivery.setText(deliveryCarregado.address.cep)
        binding.ufDetailDelivery.setText(deliveryCarregado.address.uf)
        binding.cityDetailDelivery.setText(deliveryCarregado.address.city)
        binding.neighborhoodDetailDelivery.setText(deliveryCarregado.address.neighborhood)
        binding.streetDetailDelivery.setText(deliveryCarregado.address.street)
        binding.numberDetailDelivery.setText(deliveryCarregado.address.number)
        binding.complementDetailDelivery.setText(deliveryCarregado.address.complement)

    }

}