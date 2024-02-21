package com.housepricepredictionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.housepricepredictionapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //button declaration
        val buyButton = binding.buyButton
        val sellButton = binding.sellButton

        //open activities
        buyButton.setOnClickListener {
            val intent = intent
            intent.setClass(this, HouseSelectionBuyActivity::class.java)
            startActivity(intent)
        }

        sellButton.setOnClickListener {
            val intent = intent
            intent.setClass(this, HouseSelectionSellActivity::class.java)
            startActivity(intent)
        }

    }
}