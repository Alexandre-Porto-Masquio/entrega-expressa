package com.apmasquio.entrega_expressa.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Handler
import com.apmasquio.entrega_expressa.presentation.deliverylist.DeliveryListActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, DeliveryListActivity::class.java))
        finishAfterTransition()
    }
}