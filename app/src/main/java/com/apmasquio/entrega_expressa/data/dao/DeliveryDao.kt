package com.apmasquio.entrega_expressa.data.dao

import androidx.room.*
import com.apmasquio.entrega_expressa.data.models.Delivery

@Dao
interface DeliveryDao {

    @Query("SELECT * FROM Delivery")
    fun buscaTodos(): List<Delivery>

    @Insert
    fun salva(vararg delivery: Delivery)

    @Delete
    fun delete(delivery: Delivery)

    @Update
    fun update(delivery: Delivery)

    @Query("SELECT * FROM Delivery WHERE id= :id")
    fun searchById(id: Long): Delivery?
}