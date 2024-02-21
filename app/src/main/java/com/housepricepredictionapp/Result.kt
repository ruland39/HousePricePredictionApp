package com.housepricepredictionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.housepricepredictionapp.databinding.ActivityResultBinding

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityResultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //variable declaration
        val backButton = binding.backButton
        val shareButton = binding.shareButton

        //back button
        backButton.setOnClickListener {
            finish()
        }

        //share button
        shareButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Your House Price Prediction")
            startActivity(Intent.createChooser(intent, "Share with"))
            //TODO: include screenshot to be shared to the intent
        }
    }
}