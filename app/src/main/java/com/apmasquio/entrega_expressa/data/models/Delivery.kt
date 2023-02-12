package com.apmasquio.entrega_expressa.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
data class Delivery(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val quantity: String,
    val date: String,
    val client: String,
    val cpf: String,
    val address: Address
    ) : Parcelable