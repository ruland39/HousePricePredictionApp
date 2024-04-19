class DataMapping {
    object DataPointConverter {
        // Define mappings between textual values and their corresponding numeric representations
        val stateMap = mapOf(
            "Johor" to 0,
            "Kedah" to 1,
            "Kelantan" to 2,
            "Kuala Lumpur" to 3,
            "Labuan" to 4,
            "Melaka" to 5,
            "Negeri Sembilan" to 6,
            "Pahang" to 7,
            "Penang" to 8,
            "Perak" to 9,
            "Putrajaya" to 10,
            "Sabah" to 11,
            "Sarawak" to 12,
            "Selangor" to 13,
            "Terengganu" to 14
        )

        val propertyTypeMap = mapOf(
            "Apartment" to 0,
            "Condominium" to 1,
            "Duplex" to 2,
            "Flat" to 3,
            "Service Residence" to 4,
            "Studio" to 5,
            "Townhouse Condo" to 6
        )

        val floorRangeMap = mapOf(
            "High (21-30)" to 0,
            "Low (1-10)" to 1,
            "Medium (11-20)" to 2
        )

        val tenureTypeMap = mapOf(
            "Freehold" to 0,
            "Leasehold" to 1
        )

        val landTitleMap = mapOf(
            "Bumi Lot" to 0,
            "Non Bumi Lot" to 1
        )

        val propertySizeMap = mapOf(
            "300 – 500 sq.ft." to 400,
            "500 – 700 sq.ft." to 600,
            "700 – 900 sq.ft." to 800,
            "900 – 1100 sq.ft." to 1000,
            "1100 – 1300 sq.ft." to 1200,
            "1300 – 1500 sq.ft." to 1400,
            "1500 – 1700 sq.ft." to 1600
        )

        val ageOfUnitMap = mapOf(
            "7 – 9 years" to 8,
            "10 – 13 years" to 12,
            "14 – 16 years" to 15
        )

        // Function to convert textual value to its corresponding numeric representation
        fun convertState(text: String): Int {
            return stateMap[text] ?: -1
        }

        fun convertPropertyType(text: String): Int {
            return propertyTypeMap[text] ?: -1
        }

        fun convertFloorRange(text: String): Int {
            return floorRangeMap[text] ?: -1
        }

        fun convertTenureType(text: String): Int {
            return tenureTypeMap[text] ?: -1
        }

        fun convertLandTitle(text: String): Int {
            return landTitleMap[text] ?: -1
        }

        fun convertPropertySize(text: String): Int {
            return propertySizeMap[text] ?: -1
        }

        fun convertAgeOfUnit(text: String): Int {
            return ageOfUnitMap[text] ?: -1
        }
    }
}
