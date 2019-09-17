package co.anamika.deliveryapp.ui

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import co.anamika.deliveryapp.R
import co.anamika.deliveryapp.model.Delivery
import co.anamika.deliveryapp.viewModel.ViewModelFactory
import co.anamika.deliveryapp.viewModel.OrderListViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.order_list_activity.*
import javax.inject.Inject

class OrderListActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var viewModel: OrderListViewModel
    private val adapter = DeliveryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_list_activity)
        AndroidInjection.inject(this)
        supportActionBar?.title = "Things to Deliver"
        val decoration = androidx.recyclerview.widget.DividerItemDecoration(
            this,
            androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
        )
        list.addItemDecoration(decoration)
        initAdapter()
        viewModel.searchRepo()
    }

    private fun initAdapter() {
        list.adapter = adapter
        viewModel.deliveries.observe(this, Observer<PagedList<Delivery>> {
            Log.d("Activity", "list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(this, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
        })
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            list.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            list.visibility = View.VISIBLE
        }
    }
}
