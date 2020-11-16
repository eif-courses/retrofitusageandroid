package eif.viko.lt.shop.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import eif.viko.lt.myapplication.Device
import eif.viko.lt.myapplication.R
import kotlinx.android.synthetic.main.device_list_item.view.*


class DeviceListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Device, DeviceListAdapter.DeviceViewHolder>(DeviceDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DeviceViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.device_list_item, parent, false), interaction
    )

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<Device>) {
        submitList(data.toMutableList())
    }

    inner class DeviceViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
            itemView.device_play_sound_btn.setOnClickListener(this)
            itemView.device_view3D__btn.setOnClickListener(this)
            itemView.device_add_to_cart.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)

            when (v) {
                itemView.device_play_sound_btn -> interaction?.playSound(clicked)
                itemView.device_view3D__btn -> interaction?.view3DModel(clicked)
                itemView.device_add_to_cart -> interaction?.addToCart(clicked)
                else -> interaction?.onItemClicked(clicked)
            }


        }

        fun bind(item: Device) = with(itemView) {

            itemView.device_title.text = item.title
            itemView.device_description.text = item.description
            Picasso.get().load(item.poster).into(itemView.device_image)

            // TODO: Bind the data with View
        }
    }

    interface Interaction {
        fun onItemClicked(device: Device)
        fun playSound(device: Device)
        fun view3DModel(device: Device)
        fun addToCart(device: Device)
    }

    private class DeviceDC : DiffUtil.ItemCallback<Device>() {
        override fun areItemsTheSame(
            oldItem: Device,
            newItem: Device
        ) = oldItem.audio == newItem.audio

        override fun areContentsTheSame(
            oldItem: Device,
            newItem: Device
        ) = oldItem == newItem
    }
}