package com.apmasquio.entrega_expressa.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apmasquio.entrega_expressa.data.dao.DeliveryDao
import com.apmasquio.entrega_expressa.data.models.Delivery

@Database(
    entities = [
        Delivery::class
    ],
    version = 2,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun deliveryDao(): DeliveryDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null
        fun dbInstance(context: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "entrega-expressa.db"
            )
                .build().also {
                    db = it
                }
        }
    }
}