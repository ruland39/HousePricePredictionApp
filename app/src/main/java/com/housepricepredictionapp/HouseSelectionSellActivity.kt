package com.housepricepredictionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputLayout
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

        val facilitiesChipGroup = binding.facilitiesChipGroup
        val nearbyFacilitiesChipGroup = binding.nearbyFacilitiesChipGroup


        val ageOfUnit = binding.ageOfUnitEditText
        val tenureType = binding.tenureTypeSpinner
        val landTitle = binding.landTitleSpinner
        val developer = binding.developerSpinner


        // Get the string array
        val locationAreas = resources.getStringArray(R.array.location_area_array)
        val propertyTypes = resources.getStringArray(R.array.property_type_array)
        val tenureTypes = resources.getStringArray(R.array.tenure_type_array)
        val landTitles = resources.getStringArray(R.array.land_title_array)
        val developers = resources.getStringArray(R.array.developer_array)

        // Initialize adapters for each spinner
        val locationAreaAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, locationAreas)
        val propertyTypeAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, propertyTypes)
        val tenureTypeAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tenureTypes)
        val landTitleAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, landTitles)
        val developerAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, developers)


        // Set adapters to respective AutoCompleteTextViews
        setAdapterToAutoCompleteTextView(binding.locationAreaSpinner, locationAreaAdapter)
        setAdapterToAutoCompleteTextView(binding.propertyTypeSpinner, propertyTypeAdapter)
        setAdapterToAutoCompleteTextView(binding.tenureTypeSpinner, tenureTypeAdapter)
        setAdapterToAutoCompleteTextView(binding.landTitleSpinner, landTitleAdapter)
        setAdapterToAutoCompleteTextView(binding.developerSpinner, developerAdapter)


        val proceedButton = binding.proceedButton

        val progressbar = binding.progressBarContainer
        progressbar.visibility = View.INVISIBLE


        //TODO: proceed button enable only when all fields are filled
        proceedButton.isEnabled = true

        //proceed to result
        proceedButton.setOnClickListener {

            progressbar.visibility = View.VISIBLE

            // Get the selected values from the spinners
            val selectedLocationArea = locationArea.editText?.text.toString()
            val selectedPropertyType = propertyType.editText?.text.toString()
            val selectedPropertySize = propertySize.text.toString()
            val selectedNumberOfBedroom = numberOfBedroom.text.toString()
            val selectedNumberOfBathroom = numberOfBathroom.text.toString()
            val selectedParkingLot = parkingLot.text.toString()
            val selectedFloorRange = floorRange.text.toString()
            val selectedAgeOfUnit = ageOfUnit.text.toString()
            val selectedTenureType = tenureType.editText?.text.toString()
            val selectedLandTitle = landTitle.editText?.text.toString()
            val selectedDeveloper = developer.editText?.text.toString()

            // Get the selected chips from facilitiesChipGroup
            val selectedFacilitiesChips = facilitiesChipGroup
                .checkedChipIds
                .map { findViewById<Chip>(it).text.toString() }

            // Get the selected chips from nearbyFacilitiesChipGroup
            val selectedNearbyFacilitiesChips = nearbyFacilitiesChipGroup
                .checkedChipIds
                .map { findViewById<Chip>(it).text.toString() }

            //ProgressBar Delay
            Handler(Looper.getMainLooper()).postDelayed({
                progressbar.visibility = View.INVISIBLE
            }, 3000)

            // Create an Intent
            val intent = Intent(this, Result::class.java)

            // Put the selected values as extras in the Intent
            intent.putExtra("LOCATION_AREA", selectedLocationArea)
            intent.putExtra("PROPERTY_TYPE", selectedPropertyType)
            intent.putExtra("PROPERTY_SIZE", selectedPropertySize)
            intent.putExtra("NUMBER_OF_BEDROOM", selectedNumberOfBedroom)
            intent.putExtra("NUMBER_OF_BATHROOM", selectedNumberOfBathroom)
            intent.putExtra("PARKING_LOT", selectedParkingLot)
            intent.putExtra("FLOOR_RANGE", selectedFloorRange)
            intent.putExtra("AGE_OF_UNIT", selectedAgeOfUnit)

            intent.putExtra("TENURE_TYPE", selectedTenureType)
            intent.putExtra("LAND_TITLE", selectedLandTitle)
            intent.putExtra("DEVELOPER", selectedDeveloper)

            // Put the selected chips as extras in the Intent
            intent.putStringArrayListExtra("SELECTED_FACILITIES_CHIPS", ArrayList(selectedFacilitiesChips))
            intent.putStringArrayListExtra("SELECTED_NEARBY_FACILITIES_CHIPS", ArrayList(selectedNearbyFacilitiesChips))

            intent.setClass(this, Result::class.java)
            startActivity(intent)
        }
    }

    private fun setAdapterToAutoCompleteTextView(textInputLayout: TextInputLayout, adapter: ArrayAdapter<String>) {
        val autoCompleteTextView = textInputLayout.editText as? AutoCompleteTextView
        autoCompleteTextView?.setAdapter(adapter)
    }
}