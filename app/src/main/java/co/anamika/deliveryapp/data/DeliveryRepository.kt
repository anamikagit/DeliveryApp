package co.anamika.deliveryapp.data

import androidx.paging.LivePagedListBuilder
import co.anamika.deliveryapp.api.DeliveryService
import co.anamika.deliveryapp.database.DeliveryLocalCache
import co.anamika.deliveryapp.model.DeliverieSearchResult
import javax.inject.Inject

class DeliveryRepository @Inject constructor(
    private val service: DeliveryService,
    private val cache: DeliveryLocalCache
) {

    fun search(): DeliverieSearchResult {
        val dataSourceFactory = cache.reposByName()
        val boundaryCallback = DeliveryBoundaryCallback(service, cache)
        val networkErrors = boundaryCallback.networkErrors
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
        return DeliverieSearchResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}