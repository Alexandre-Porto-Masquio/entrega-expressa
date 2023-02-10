package com.apmasquio.entrega_expressa.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.apmasquio.`entrega-expressa`.R
import com.github.apmasquio.`entrega-expressa`.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}