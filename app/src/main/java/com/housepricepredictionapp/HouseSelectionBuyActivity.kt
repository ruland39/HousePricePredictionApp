package com.housepricepredictionapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputLayout
import com.housepricepredictionapp.databinding.ActivityHouseSelectionBuyBinding

class HouseSelectionBuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHouseSelectionBuyBinding = ActivityHouseSelectionBuyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ML Model
        val modelPath = "ensemble_model.tflite"
        val predictor = HousePricePredictor(this, modelPath)

        // Variable declaration
        val locationArea = binding.locationAreaSpinner
        val propertyType = binding.propertyTypeSpinner
        val propertySize = binding.propertySizeSpinner
        val numberOfBedroom = binding.numberOfBedroomSpinner
        val numberOfBathroom = binding.numberOfBathroomSpinner
        val parkingLot = binding.parkingLotSpinner
        val facilitiesChipGroup = binding.facilitiesChipGroup
        val nearbyFacilitiesChipGroup = binding.nearbyFacilitiesChipGroup
        val ageOfUnit = binding.ageOfUnitSpinner
        val tenureType = binding.tenureTypeSpinner
        val landTitle = binding.landTitleSpinner

        // Get the string array
        val locationAreas = resources.getStringArray(R.array.location_area_array)
        val propertyTypes = resources.getStringArray(R.array.property_type_array)
        val propertySizes = resources.getStringArray(R.array.property_size_array)
        val numberOfBedrooms = resources.getStringArray(R.array.number_of_bedroom_array)
        val numberOfBathrooms = resources.getStringArray(R.array.number_of_bathroom_array)
        val parkingLots = resources.getStringArray(R.array.parking_lot_array)
        val ageOfUnits = resources.getStringArray(R.array.age_of_unit_array)
        val tenureTypes = resources.getStringArray(R.array.tenure_type_array)
        val landTitles = resources.getStringArray(R.array.land_title_array)

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
        val ageOfUnitAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, ageOfUnits)
        val tenureTypeAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tenureTypes)
        val landTitleAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, landTitles)

        // Set adapters to respective AutoCompleteTextViews
        setAdapterToAutoCompleteTextView(binding.locationAreaSpinner, locationAreaAdapter)
        setAdapterToAutoCompleteTextView(binding.propertyTypeSpinner, propertyTypeAdapter)
        setAdapterToAutoCompleteTextView(binding.propertySizeSpinner, propertySizeAdapter)
        setAdapterToAutoCompleteTextView(binding.numberOfBedroomSpinner, numberOfBedroomAdapter)
        setAdapterToAutoCompleteTextView(binding.numberOfBathroomSpinner, numberOfBathroomAdapter)
        setAdapterToAutoCompleteTextView(binding.parkingLotSpinner, parkingLotAdapter)
        setAdapterToAutoCompleteTextView(binding.ageOfUnitSpinner, ageOfUnitAdapter)
        setAdapterToAutoCompleteTextView(binding.tenureTypeSpinner, tenureTypeAdapter)
        setAdapterToAutoCompleteTextView(binding.landTitleSpinner, landTitleAdapter)

        val proceedButton = binding.proceedButton
        val progressbar = binding.progressBarContainer
        progressbar.visibility = View.INVISIBLE
        proceedButton.isEnabled = true

        // Proceed to result
        proceedButton.setOnClickListener {

            // Check if all fields are filled
            val isFieldsFilled = isFieldsFilled(
                locationArea,
                propertyType,
                propertySize,
                numberOfBedroom,
                numberOfBathroom,
                parkingLot,
                ageOfUnit,
                tenureType,
                landTitle
            )

            if(isFieldsFilled){
                progressbar.visibility = View.VISIBLE

                // Get the selected values from the UI components
                val selectedLocationArea = locationArea.editText?.text.toString()
                val selectedPropertyType = propertyType.editText?.text.toString()
                val selectedPropertySize = propertySize.editText?.text.toString()
                val selectedNumberOfBedroom = numberOfBedroom.editText?.text.toString()
                val selectedNumberOfBathroom = numberOfBathroom.editText?.text.toString()
                val selectedParkingLot = parkingLot.editText?.text.toString()
                val selectedAgeOfUnit = ageOfUnit.editText?.text.toString()
                val selectedTenureType = tenureType.editText?.text.toString()
                val selectedLandTitle = landTitle.editText?.text.toString()

                // Convert selected values to their corresponding numeric representations
                val newDataPoint = mutableListOf(
                    DataMapping.DataPointConverter.convertState(selectedLocationArea),
                    DataMapping.DataPointConverter.convertPropertyType(selectedPropertyType),
                    DataMapping.DataPointConverter.convertPropertySize(selectedPropertySize),
                    selectedNumberOfBedroom.toInt(),
                    selectedNumberOfBathroom.toInt(),
                    selectedParkingLot.toInt(),
                    DataMapping.DataPointConverter.convertAgeOfUnit(selectedAgeOfUnit),
                    DataMapping.DataPointConverter.convertTenureType(selectedTenureType),
                    DataMapping.DataPointConverter.convertLandTitle(selectedLandTitle)
                )

                // Get the selected chips from facilitiesChipGroup
                val selectedFacilitiesChips = facilitiesChipGroup.checkedChipIds
                    .map { findViewById<Chip>(it).text.toString() }

                // Get the selected chips from nearbyFacilitiesChipGroup
                val selectedNearbyFacilitiesChips = nearbyFacilitiesChipGroup.checkedChipIds
                    .map { findViewById<Chip>(it).text.toString() }

                // Include facilities and nearby facilities in newDataPoint
                val facilities = listOf(
                    "\uD83C\uDFCB\uFE0F Gymnasium",
                    "\uD83C\uDFCA Swimming Pool",
                    "\uD83C\uDFC3\u200D♂\uFE0F Jogging Track",
                    "\uD83D\uDEDD Playground",
                    "\uD83D\uDED2 Minimart",
                    "\uD83C\uDFDF\uFE0F Multipurpose Hall",
                    "\uD83C\uDF0A Club house"
                )

                val nearbyFacilities = listOf(
                    "\uD83D\uDE86 Railway Station",
                    "\uD83D\uDE8D Bus Stop",
                    "\uD83D\uDE98 Highway",
                    "\uD83D\uDECD\uFE0F Mall",
                    "\uD83C\uDFEB School",
                    "\uD83C\uDFE5 Hospital",
                    "\uD83C\uDFDE\uFE0F Park"
                )

                // Add values for facilities
                for (facility in facilities) {
                    newDataPoint.add(if (facility in selectedFacilitiesChips) 1 else 0)
                }

                // Add values for nearby facilities
                for (facility in nearbyFacilities) {
                    newDataPoint.add(if (facility in selectedNearbyFacilitiesChips) 1 else 0)
                }

                // Print or use the new data point as needed
                println("new_data_point = [$newDataPoint]")
                Log.d("new_data_point = [$newDataPoint]", newDataPoint.toString())

                // Make prediction using the model
                val inputData = newDataPoint.map { it.toFloat() }.toFloatArray()
                val predictedPrice = predictor.predict(inputData)
                val predictedPriceString = predictedPrice.toInt().toString()

                // ProgressBar Delay
                Handler(Looper.getMainLooper()).postDelayed({
                    progressbar.visibility = View.INVISIBLE
                }, 3000)

                // Create an Intent
                val intent = Intent(this, Result::class.java)

                // Put the selected values as extras in the Intent
                intent.putStringArrayListExtra("NEW_DATA_POINT", ArrayList(newDataPoint.map { it.toString() }))
                intent.putExtra("LOCATION_AREA", selectedLocationArea)
                intent.putExtra("PROPERTY_TYPE", selectedPropertyType)
                intent.putExtra("PROPERTY_SIZE", selectedPropertySize)
                intent.putExtra("NUMBER_OF_BEDROOM", selectedNumberOfBedroom)
                intent.putExtra("NUMBER_OF_BATHROOM", selectedNumberOfBathroom)
                intent.putExtra("PARKING_LOT", selectedParkingLot)
                intent.putExtra("AGE_OF_UNIT", selectedAgeOfUnit)
                intent.putExtra("TENURE_TYPE", selectedTenureType)
                intent.putExtra("LAND_TITLE", selectedLandTitle)
                intent.putExtra("PREDICTED_PRICE", predictedPriceString)

                // Put the selected chips as extras in the Intent
                intent.putStringArrayListExtra("SELECTED_FACILITIES_CHIPS", ArrayList(selectedFacilitiesChips))
                intent.putStringArrayListExtra("SELECTED_NEARBY_FACILITIES_CHIPS", ArrayList(selectedNearbyFacilitiesChips))

                intent.setClass(this, Result::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }




        }

    }

    private fun setAdapterToAutoCompleteTextView(
        textInputLayout: TextInputLayout,
        adapter: ArrayAdapter<String>
    ) {
        val autoCompleteTextView = textInputLayout.editText as? AutoCompleteTextView
        autoCompleteTextView?.setAdapter(adapter)
    }

    // Function to check if all fields are filled
    private fun isFieldsFilled(vararg fields: TextInputLayout): Boolean {
        for (field in fields) {
            if (field.editText?.text.isNullOrBlank()) {
                return false
            }
        }
        return true
    }
}
