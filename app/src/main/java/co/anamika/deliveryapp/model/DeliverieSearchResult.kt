package co.anamika.deliveryapp.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class DeliverieSearchResult(
    val data: LiveData<PagedList<Delivery>>,
    val networkErrors: LiveData<String>
)