package co.anamika.deliveryapp.database

import androidx.paging.DataSource
import co.anamika.deliveryapp.model.Delivery
import java.util.concurrent.Executors
import javax.inject.Inject

class DeliveryLocalCache @Inject constructor(
    private val database: DeliveryDatabase
) {

    fun insert(deliveries: List<Delivery>, insertFinished: () -> Unit) {
        Executors.newSingleThreadExecutor()
            .execute {
                database.deliverieDao().insert(deliveries)
                insertFinished()
            }
    }

    fun reposByName(): DataSource.Factory<Int, Delivery> {
        return database.deliverieDao().reposByName()
    }
}