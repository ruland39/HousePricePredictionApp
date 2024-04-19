package com.housepricepredictionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
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
        //make priceValue a random generated number from 100,000 to 1,000,000
//        val priceValue = (100000..1000000).random().toString()
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
        val selectedFacilitiesChips = intent.getStringArrayListExtra("SELECTED_FACILITIES_CHIPS")
        val selectedNearbyFacilitiesChips = intent.getStringArrayListExtra("SELECTED_NEARBY_FACILITIES_CHIPS")
        val predictedPrice = intent.getStringExtra("PREDICTED_PRICE")

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

        val facilities = binding.facilitiesChipGroup
        val nearbyFacilities = binding.nearbyFacilitiesChipGroup

        val ageOfUnit = binding.ageOfUnitTextView
        val tenureType = binding.tenureTypeTextView
        val landTitle = binding.landTitleTextView

        // Display selected values
        price.text = predictedPrice
        locationArea.text = locationAreaValue
        propertyType.text = propertyTypeValue
        propertySize.text = propertySizeValue
        numberOfBedroom.text = numberOfBedroomValue
        numberOfBathroom.text = numberOfBathroomValue
        parkingLot.text = parkingLotValue
        ageOfUnit.text = ageOfUnitValue
        tenureType.text = tenureTypeValue
        landTitle.text = landTitleValue

        // Clear existing chips
        facilities.removeAllViews()
        nearbyFacilities.removeAllViews()

        // Create and add chips for selected facilities
        selectedFacilitiesChips?.forEach { facility ->
            val chip = Chip(this)
            chip.text = facility

            chip.isSelected = true
            chip.isEnabled = true
            chip.isCheckable = true
            chip.isChecked = true
            chip.chipBackgroundColor = ContextCompat.getColorStateList(this, R.color.red_transparent)

            facilities.addView(chip)
        }

        // Create and add chips for selected nearby facilities
        selectedNearbyFacilitiesChips?.forEach { nearbyFacility ->
            val chip = Chip(this)
            chip.text = nearbyFacility

            chip.isSelected = true
            chip.isEnabled = true
            chip.isCheckable = true
            chip.isChecked = true
            chip.chipBackgroundColor = ContextCompat.getColorStateList(this, R.color.red_transparent)
            nearbyFacilities.addView(chip)
        }


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
                Price: $predictedPrice
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