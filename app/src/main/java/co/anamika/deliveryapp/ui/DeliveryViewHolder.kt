package co.anamika.deliveryapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.anamika.deliveryapp.R
import co.anamika.deliveryapp.model.Delivery
import com.squareup.picasso.Picasso

class DeliveryViewHolder(view: View, context: Context) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    private val description: TextView = view.findViewById(R.id.deliveryDiscription)
    private val image: ImageView = view.findViewById(R.id.image)
    private val context: Context = context

    private var delivery: Delivery? = null

    init {
        view.setOnClickListener {
            val intent = Intent(context, DeliveryDetailActivity::class.java)
            intent.putExtra("desc", delivery?.description)
            intent.putExtra("imageUrl", delivery?.imageUrl)
            intent.putExtra("lat", delivery?.latitude)
            intent.putExtra("lng", delivery?.longitude)
            intent.putExtra("address", delivery?.address)
            context.startActivity(intent)
        }
    }

    fun bind(delivery: Delivery?) {
        if (delivery == null) {
            val resources = itemView.resources
            description.text = resources.getString(R.string.loading)
        } else {
            showRepoData(delivery)
        }
    }

    private fun showRepoData(delivery: Delivery) {
        this.delivery = delivery
        description.text = "${delivery.description} at ${delivery.address}"
        Picasso.with(context)
            .load(delivery.imageUrl)
            .resize(300, 200)
            .centerCrop()
            .into(image)
    }

    companion object {
        fun create(parent: ViewGroup): DeliveryViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.deliverie_view_item, parent, false)
            return DeliveryViewHolder(view, parent.context)
        }
    }
}