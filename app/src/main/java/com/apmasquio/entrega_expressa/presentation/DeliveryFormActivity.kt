package com.apmasquio.entrega_expressa.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.apmasquio.entrega_expressa.R
import com.apmasquio.entrega_expressa.data.AppDatabase
import com.apmasquio.entrega_expressa.data.models.Delivery
import com.apmasquio.entrega_expressa.databinding.ActivityDeliveryFormBinding
import com.apmasquio.entrega_expressa.utils.Constants.DELIVERY_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeliveryFormActivity :
    AppCompatActivity(R.layout.activity_delivery_form) {
    private lateinit var delivery: Delivery
    private lateinit var formBinding: ActivityDeliveryFormBinding
    private var deliveryId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Cadastrar Entrega"
        formBinding = ActivityDeliveryFormBinding.inflate(layoutInflater)
        val formView = formBinding.root
        setContentView(formView)

        val thisContext = this

        intent.getParcelableExtra<Delivery>(DELIVERY_KEY)?.let { loadedDelivery ->
            delivery = loadedDelivery
            deliveryId = loadedDelivery.id
            fillForm(loadedDelivery)
        }

        configureSaveButton()

    }

    override fun onResume() {
        super.onResume()
    }

    private fun configureSaveButton() {
        val saveButton = formBinding.btSaveDeliveryForm
        saveButton.setOnClickListener {

            if (validateFields()){
                val db = AppDatabase.dbInstance(this)
                val deliveryDao = db.deliveryDao()
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        if (deliveryId > 0 && ::delivery.isInitialized) {
                            deliveryDao.update(createDelivery())
                        } else {
                            deliveryDao.save(createDelivery())
                        }
                    }
                }
                finish()
            } else {
                Toast.makeText(this, "Por favor, preencha os campos necessários.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateFields(): Boolean {
        var bool = true
        if (formBinding.textNameDeliveryForm.text!!.isEmpty()) {
            formBinding.textNameDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        if (formBinding.textQuantityDeliveryForm.text!!.isEmpty()) {
            formBinding.textQuantityDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        if (formBinding.textDateDeliveryForm.text!!.isEmpty()) {
            formBinding.textDateDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        if (formBinding.textClientDeliveryForm.text!!.isEmpty()) {
            formBinding.textClientDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        if (formBinding.textCepDeliveryForm.text!!.isEmpty()) {
            formBinding.textCepDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        if (formBinding.textCpfDeliveryForm.text!!.isEmpty()) {
            formBinding.textCpfDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        if (formBinding.textUfDeliveryForm.text!!.isEmpty()) {
            formBinding.textUfDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        if (formBinding.textCityDeliveryForm.text!!.isEmpty()) {
            formBinding.textCityDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        if (formBinding.textNeighborhoodDeliveryForm.text!!.isEmpty()) {
            formBinding.textNeighborhoodDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        if (formBinding.textStreetDeliveryForm.text!!.isEmpty()) {
            formBinding.textStreetDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        if (formBinding.textNumberDeliveryForm.text!!.isEmpty()) {
            formBinding.textNumberDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        if (formBinding.textComplementDeliveryForm.text!!.isEmpty()) {
            formBinding.textComplementDeliveryForm.error = "este campo é obrigatório"
            bool = false
        }
        return bool
    }

    private fun createDelivery(): Delivery {
        val fieldName = formBinding.textNameDeliveryForm
        val name = fieldName.text.toString()

        val fieldQuantity = formBinding.textQuantityDeliveryForm

        val quantity = fieldQuantity.text.toString()

        val fieldDate = formBinding.textDateDeliveryForm
        val date = fieldDate.text.toString()

        val fieldClient = formBinding.textClientDeliveryForm
        val client = fieldClient.text.toString()

        val fieldCpf = formBinding.textCpfDeliveryForm
        val cpf = fieldCpf.text.toString()

        val fieldCep = formBinding.textCepDeliveryForm
        val cep = fieldCep.text.toString()

        val fieldUf = formBinding.textUfDeliveryForm
        val uf = fieldUf.text.toString()

        val fieldCity = formBinding.textCityDeliveryForm
        val city = fieldCity.text.toString()

        val fieldNeighborhood = formBinding.textNeighborhoodDeliveryForm
        val neighborhood = fieldNeighborhood.text.toString()

        val fieldStreet = formBinding.textStreetDeliveryForm
        val street = fieldStreet.text.toString()

        val fieldNumber = formBinding.textNumberDeliveryForm
        val number = fieldNumber.text.toString()

        val fieldComplement = formBinding.textComplementDeliveryForm
        val complement = fieldComplement.text.toString()

        return Delivery(
            id = deliveryId,
            name = name,
            quantity = quantity,
            date = date,
            client = client,
            cpf = cpf,
            cep = cep,
            uf = uf,
            city = city,
            neighborhood = neighborhood,
            street = street,
            number = number,
            complement = complement
        )
    }

    private fun fillForm(loadedDelivery: Delivery) {
        formBinding.textNameDeliveryForm.setText(loadedDelivery.name)
        formBinding.textQuantityDeliveryForm.setText(loadedDelivery.quantity)
        formBinding.textDateDeliveryForm.setText(loadedDelivery.date)
        formBinding.textClientDeliveryForm.setText(loadedDelivery.client)
        formBinding.textCpfDeliveryForm.setText(loadedDelivery.cpf)
        formBinding.textCepDeliveryForm.setText(loadedDelivery.cep)
        formBinding.textUfDeliveryForm.setText(loadedDelivery.uf)
        formBinding.textCityDeliveryForm.setText(loadedDelivery.city)
        formBinding.textNeighborhoodDeliveryForm.setText(loadedDelivery.neighborhood)
        formBinding.textStreetDeliveryForm.setText(loadedDelivery.street)
        formBinding.textNumberDeliveryForm.setText(loadedDelivery.number)
        formBinding.textComplementDeliveryForm.setText(loadedDelivery.complement)
    }


}
