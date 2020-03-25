package com.example.yelpclone

import com.google.gson.annotations.SerializedName
import java.util.*

data class YelpSearchResult(
    @SerializedName("total") val total: Int,
    @SerializedName("businesses") val restaurants: List<YelpRestaurant>,
    @SerializedName("categories") val listOfCategories: List<YelpCategoryList>


    )

data class YelpCategoryList(
    val categories: List<Objects>,
    val title: String
)

data class YelpRestaurant(
    val name: String,
    val rating: Double,
    val price: String,
    val categories: List<YelpCategory>,
    val location: YelpLocation,
    val url: String,
    val id: String,
    @SerializedName("review_count") val numReviews: String,
    @SerializedName("distance") val distanceInMeters: Double,
    @SerializedName("image_url") val imageUrl: String

) {
    fun displayDistance(): String{
        val milesPerMeter = .000621371
        val distanceInMiles = "%.2f".format(distanceInMeters * milesPerMeter)
        return "$distanceInMiles mi"
    }

}

data class YelpCategory(
    val title: String
)

data class YelpLocation(
    @SerializedName("address1") val address: String
)

