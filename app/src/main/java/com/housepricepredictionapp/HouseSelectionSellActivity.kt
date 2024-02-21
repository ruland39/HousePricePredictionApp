package com.housepricepredictionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.housepricepredictionapp.databinding.ActivityHouseSelectionSellBinding

class HouseSelectionSellActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHouseSelectionSellBinding = ActivityHouseSelectionSellBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //variable declaration
        val locationArea = binding.locationAreaSpinner
        val propertyType = binding.propertyTypeSpinner
        val propertySize = binding.propertySizeEditText
        val numberOfBedroom = binding.numberOfBedroomEditText
        val numberOfBathroom = binding.numberOfBathroomEditText
        val parkingLot = binding.parkingLotEditText
        val floorRange= binding.floorRangeEditText

        //TODO: add Facilities

        //TODO: add Nearby Facilities


        val ageOfUnit = binding.ageOfUnitEditText
        val tenureType = binding.tenureTypeSpinner
        val landTitle = binding.landTitleSpinner
        val developer = binding.developerSpinner

        val proceedButton = binding.proceedButton

        //proceed button enable only when all fields are filled
        proceedButton.isEnabled = true

        //proceed to result
        proceedButton.setOnClickListener {
            val intent = intent
            intent.setClass(this, Result::class.java)
            startActivity(intent)
        }
    }
}