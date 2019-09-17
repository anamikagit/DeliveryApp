package co.anamika.deliveryapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import co.anamika.deliveryapp.model.DeliverieSearchResult
import co.anamika.deliveryapp.model.Delivery
import co.anamika.deliveryapp.data.DeliveryRepository
import javax.inject.Inject

class OrderListViewModel @Inject constructor(private val repository: DeliveryRepository) : ViewModel() {

    private val deliveriesLiveData = MutableLiveData<DeliverieSearchResult>()
    val deliveries: LiveData<PagedList<Delivery>> =
        Transformations.switchMap(deliveriesLiveData) { it.data }
    val networkErrors: LiveData<String> =
        Transformations.switchMap(deliveriesLiveData) { it.networkErrors }

    fun searchRepo() {
        deliveriesLiveData.postValue(repository.search())
    }
}