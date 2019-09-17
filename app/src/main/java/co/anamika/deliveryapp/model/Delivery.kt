package co.anamika.deliveryapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "deliveries")
class Delivery(
    @PrimaryKey @field:SerializedName("id") val id: Long,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("imageUrl") val imageUrl: String,
    @field:SerializedName("latitude") val latitude: Double,
    @field:SerializedName("longitude") val longitude: Double,
    @field:SerializedName("address") val address: String
)