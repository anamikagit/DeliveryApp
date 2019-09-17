package co.anamika.deliveryapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import co.anamika.deliveryapp.api.DeliveryService
import co.anamika.deliveryapp.api.searchDeliveries
import co.anamika.deliveryapp.database.DeliveryLocalCache
import co.anamika.deliveryapp.model.Delivery

class DeliveryBoundaryCallback(
    private val service: DeliveryService,
    private val cache: DeliveryLocalCache
) : PagedList.BoundaryCallback<Delivery>() {

    private var offset = 0
    private val limit = 20
    private val _networkErrors = MutableLiveData<String>()
    val networkErrors: LiveData<String> get() = _networkErrors
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Delivery) {
        super.onItemAtEndLoaded(itemAtEnd)
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return
        isRequestInProgress = true
        searchDeliveries(service, offset, limit, { deliveries ->
            cache.insert(deliveries) {
                offset += NETWORK_PAGE_SIZE
                isRequestInProgress = false
            }
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 40
    }
}