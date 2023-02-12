package com.apmasquio.entrega_expressa.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apmasquio.entrega_expressa.data.models.Delivery
import com.apmasquio.entrega_expressa.databinding.DeliveryItemBinding
import com.apmasquio.entrega_expressa.utils.Constants.DELIVERY_KEY
import com.apmasquio.entrega_expressa.presentation.DeliveryDetailsActivity

class DeliveryListAdapter(
    deliveries : List<Delivery> = emptyList()
) : RecyclerView.Adapter<DeliveryListAdapter.ViewHolder>() {

    private val deliveries = deliveries.toMutableList()

    class ViewHolder(private val binding: DeliveryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(delivery: Delivery) {
            //Click leads to detail screen
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DeliveryDetailsActivity::class.java)
                intent.putExtra(DELIVERY_KEY, delivery)
                binding.root.context.startActivity(intent)
            }

            val nome = binding.nameDeliveryItem
            nome.text = delivery.name

            val quantity = binding.quantityDeliveryItem
            quantity.text = delivery.quantity

            val date = binding.dateDeliveryItem
            date.text = delivery.date

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DeliveryItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val delivery = deliveries[position]
        holder.vincula(delivery)
    }

    override fun getItemCount(): Int = deliveries.size

    fun atualiza(deliveries: List<Delivery>) {
        this.deliveries.clear()
        this.deliveries.addAll(deliveries)
        notifyDataSetChanged()
    }

}
