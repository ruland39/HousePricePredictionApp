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

        // Get the intent that started this activity
        val intent = intent

        // Get the selected values from the intent extras
//        val priceValue = intent.getStringExtra("PRICE")
        val priceValue = "340 000"
        val locationAreaValue = intent.getStringExtra("LOCATION_AREA")
        val propertyTypeValue = intent.getStringExtra("PROPERTY_TYPE")
        val propertySizeValue = intent.getStringExtra("PROPERTY_SIZE")
        val numberOfBedroomValue = intent.getStringExtra("NUMBER_OF_BEDROOM")
        val numberOfBathroomValue = intent.getStringExtra("NUMBER_OF_BATHROOM")
        val parkingLotValue = intent.getStringExtra("PARKING_LOT")
        val floorRangeValue = intent.getStringExtra("FLOOR_RANGE")
        val ageOfUnitValue = intent.getStringExtra("AGE_OF_UNIT")
        val tenureTypeValue = intent.getStringExtra("TENURE_TYPE")
        val landTitleValue = intent.getStringExtra("LAND_TITLE")
        val developerValue = intent.getStringExtra("DEVELOPER")

        //variable declaration
        val backButton = binding.backButton
        val shareButton = binding.shareButton

        val price = binding.price
        val locationArea = binding.locationAreaTextView
        val propertyType = binding.propertyTypeTextView
        val propertySize = binding.propertySizeTextView
        val numberOfBedroom = binding.numberOfBedroomTextView
        val numberOfBathroom = binding.numberOfBathroomTextView
        val parkingLot = binding.parkingLotTextView
        val floorRange = binding.floorRangeTextView
        val ageOfUnit = binding.ageOfUnitTextView
        val tenureType = binding.tenureTypeTextView
        val landTitle = binding.landTitleTextView
        val developer = binding.developerTextView

        // Display selected values
        price.text = priceValue
        locationArea.text = locationAreaValue
        propertyType.text = propertyTypeValue
        propertySize.text = propertySizeValue
        numberOfBedroom.text = numberOfBedroomValue
        numberOfBathroom.text = numberOfBathroomValue
        parkingLot.text = parkingLotValue
        floorRange.text = floorRangeValue
        ageOfUnit.text = ageOfUnitValue
        tenureType.text = tenureTypeValue
        landTitle.text = landTitleValue
        developer.text = developerValue

        //back button
        backButton.setOnClickListener {
            finish()
        }

        // Share button
        //TODO: get screenshot of the result page
        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareMessage = """
                Price: $priceValue
                Location Area: $locationAreaValue
                Property Type: $propertyTypeValue
                Property Size: $propertySizeValue
                Number of Bedrooms: $numberOfBedroomValue
                Number of Bathrooms: $numberOfBathroomValue
                Parking Lot: $parkingLotValue
                Floor Range: $floorRangeValue
                Age of Unit: $ageOfUnitValue
                Tenure Type: $tenureTypeValue
                Land Title: $landTitleValue
                Developer: $developerValue
            """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}