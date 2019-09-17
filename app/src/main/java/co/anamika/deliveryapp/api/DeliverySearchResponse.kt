package co.anamika.deliveryapp.api

import com.google.gson.annotations.SerializedName

data class DeliverySearchResponse(
        @SerializedName("id") val id: Long = 0,
        @SerializedName("description") val description: String,
        @SerializedName("imageUrl") val imageUrl: String,
        @SerializedName("location") val location: Location
)
