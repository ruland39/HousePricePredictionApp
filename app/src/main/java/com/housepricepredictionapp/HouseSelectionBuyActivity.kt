package com.housepricepredictionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import com.housepricepredictionapp.databinding.ActivityHouseSelectionBuyBinding

class HouseSelectionBuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHouseSelectionBuyBinding = ActivityHouseSelectionBuyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //variable declaration
        val locationArea = binding.locationAreaSpinner
        val propertyType = binding.propertyTypeSpinner
        val propertySize = binding.propertySizeSpinner
        val numberOfBedroom = binding.numberOfBedroomSpinner
        val numberOfBathroom = binding.numberOfBathroomSpinner
        val parkingLot = binding.parkingLotSpinner
        val floorRange = binding.floorRangeSpinner

        //TODO: add Facilities

        //TODO: add Nearby Facilities


        val ageOfUnit = binding.ageOfUnitSpinner
        val tenureType = binding.tenureTypeSpinner
        val landTitle = binding.landTitleSpinner
        val developer = binding.developerSpinner

        val proceedButton = binding.proceedButton

        // Get the string array
        val locationAreas = resources.getStringArray(R.array.location_area_array)
        val propertyTypes = resources.getStringArray(R.array.property_type_array)
        val propertySizes = resources.getStringArray(R.array.property_size_array)
        val numberOfBedrooms = resources.getStringArray(R.array.number_of_bedroom_array)
        val numberOfBathrooms = resources.getStringArray(R.array.number_of_bathroom_array)
        val parkingLots = resources.getStringArray(R.array.parking_lot_array)
        val floorRanges = resources.getStringArray(R.array.floor_range_array)
        val ageOfUnits = resources.getStringArray(R.array.age_of_unit_array)
        val tenureTypes = resources.getStringArray(R.array.tenure_type_array)
        val landTitles = resources.getStringArray(R.array.land_title_array)
        val developers = resources.getStringArray(R.array.developer_array)

        // Initialize adapters for each spinner
        val locationAreaAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, locationAreas)
        val propertyTypeAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, propertyTypes)
        val propertySizeAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, propertySizes)
        val numberOfBedroomAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, numberOfBedrooms)
        val numberOfBathroomAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, numberOfBathrooms)
        val parkingLotAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, parkingLots)
        val floorRangeAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, floorRanges)
        val ageOfUnitAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, ageOfUnits)
        val tenureTypeAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tenureTypes)
        val landTitleAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, landTitles)
        val developerAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, developers)

        // Set adapters to respective AutoCompleteTextViews
        setAdapterToAutoCompleteTextView(binding.locationAreaSpinner, locationAreaAdapter)
        setAdapterToAutoCompleteTextView(binding.propertyTypeSpinner, propertyTypeAdapter)
        setAdapterToAutoCompleteTextView(binding.propertySizeSpinner, propertySizeAdapter)
        setAdapterToAutoCompleteTextView(binding.numberOfBedroomSpinner, numberOfBedroomAdapter)
        setAdapterToAutoCompleteTextView(binding.numberOfBathroomSpinner, numberOfBathroomAdapter)
        setAdapterToAutoCompleteTextView(binding.parkingLotSpinner, parkingLotAdapter)
        setAdapterToAutoCompleteTextView(binding.floorRangeSpinner, floorRangeAdapter)
        setAdapterToAutoCompleteTextView(binding.ageOfUnitSpinner, ageOfUnitAdapter)
        setAdapterToAutoCompleteTextView(binding.tenureTypeSpinner, tenureTypeAdapter)
        setAdapterToAutoCompleteTextView(binding.landTitleSpinner, landTitleAdapter)
        setAdapterToAutoCompleteTextView(binding.developerSpinner, developerAdapter)


        //proceed button enable only when all fields are filled
        proceedButton.isEnabled = true

        //proceed to result
        proceedButton.setOnClickListener {
            val intent = intent
            intent.setClass(this, Result::class.java)
            startActivity(intent)
        }
    }

    private fun setAdapterToAutoCompleteTextView(textInputLayout: TextInputLayout, adapter: ArrayAdapter<String>) {
        val autoCompleteTextView = textInputLayout.editText as? AutoCompleteTextView
        autoCompleteTextView?.setAdapter(adapter)
    }
}