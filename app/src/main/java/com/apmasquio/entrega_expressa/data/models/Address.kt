package com.apmasquio.entrega_expressa.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Address(
    @PrimaryKey(autoGenerate = true)
    val cep: String,
    val uf: String,
    val city: String,
    val neighborhood: String,
    val street: String,
    val number: String,
    val complement: String
    ) : Parcelable