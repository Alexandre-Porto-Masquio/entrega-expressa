package com.apmasquio.entrega_expressa.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apmasquio.entrega_expressa.R
import com.apmasquio.entrega_expressa.data.AppDatabase
import com.apmasquio.entrega_expressa.data.models.Address
import com.apmasquio.entrega_expressa.data.models.Delivery
import com.apmasquio.entrega_expressa.databinding.ActivityDeliveryFormBinding
import com.apmasquio.entrega_expressa.utils.Constants.DELIVERY_KEY

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

        configuraBotaoSalvar()

    }

    override fun onResume() {
        super.onResume()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = formBinding.btSaveDeliveryForm
        botaoSalvar.setOnClickListener {

            val db = AppDatabase.dbInstance(this)
            val deliveryDao = db.deliveryDao()
            if (deliveryId > 0 && ::delivery.isInitialized) {
                deliveryDao.update(createDelivery())
            } else {
                deliveryDao.salva(createDelivery())
            }
            finish()
        }
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

        val newAddress = Address(
            cep = cep,
            uf = uf,
            city = city,
            neighborhood = neighborhood,
            street = street,
            number = number,
            complement = complement
        )

        return Delivery(
            id = deliveryId,
            name = name,
            quantity = quantity,
            date = date,
            client = client,
            cpf = cpf,
            address = newAddress
        )
    }

    private fun fillForm(loadedDelivery: Delivery) {
        formBinding.textNameDeliveryForm.setText(loadedDelivery.name)
        formBinding.textQuantityDeliveryForm.setText(loadedDelivery.quantity)
        formBinding.textDateDeliveryForm.setText(loadedDelivery.date)
        formBinding.textClientDeliveryForm.setText(loadedDelivery.client)
        formBinding.textCpfDeliveryForm.setText(loadedDelivery.cpf)
        formBinding.textCepDeliveryForm.setText(loadedDelivery.address.cep)
        formBinding.textUfDeliveryForm.setText(loadedDelivery.address.uf)
        formBinding.textCityDeliveryForm.setText(loadedDelivery.address.city)
        formBinding.textNeighborhoodDeliveryForm.setText(loadedDelivery.address.neighborhood)
        formBinding.textStreetDeliveryForm.setText(loadedDelivery.address.street)
        formBinding.textNumberDeliveryForm.setText(loadedDelivery.address.number)
        formBinding.textComplementDeliveryForm.setText(loadedDelivery.address.complement)
    }


}
