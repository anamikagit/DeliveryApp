package co.anamika.deliveryapp.ui

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import android.view.ViewGroup
import co.anamika.deliveryapp.model.Delivery

class DeliveryAdapter : PagedListAdapter<Delivery, androidx.recyclerview.widget.RecyclerView.ViewHolder>(
    REPO_COMPARATOR
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return DeliveryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as DeliveryViewHolder).bind(repoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Delivery>() {
            override fun areItemsTheSame(oldItem: Delivery, newItem: Delivery): Boolean =
                oldItem.description == newItem.description

            override fun areContentsTheSame(oldItem: Delivery, newItem: Delivery): Boolean =
                oldItem == newItem
        }
    }
}



