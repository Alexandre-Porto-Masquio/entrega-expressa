package com.apmasquio.entrega_expressa.di

import android.app.Application
import androidx.room.Room
import com.apmasquio.entrega_expressa.data.AppDatabase
import com.apmasquio.entrega_expressa.data.dao.DeliveryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideDeliveryDao(application: Application): DeliveryDao {
        return AppDatabase.dbInstance(application.applicationContext).deliveryDao()
    }
}