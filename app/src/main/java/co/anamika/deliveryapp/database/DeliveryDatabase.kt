package co.anamika.deliveryapp.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import co.anamika.deliveryapp.model.Delivery

@Database(
    entities = [Delivery::class],
    version = 1,
    exportSchema = false
)
abstract class DeliveryDatabase : RoomDatabase() {
    abstract fun deliverieDao(): DeliveryDao

    companion object {
        @Volatile
        private var INSTANCE: DeliveryDatabase? = null

        fun getInstance(context: Context): DeliveryDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DeliveryDatabase::class.java, "Delivery.db"
            ).build()
    }
}