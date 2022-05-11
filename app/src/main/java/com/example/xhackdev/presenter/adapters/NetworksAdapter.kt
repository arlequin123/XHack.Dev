package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.data.models.UserDetailsRequestDto
import com.example.xhackdev.databinding.BottomSheetRequestItemBinding
import com.example.xhackdev.databinding.NetworkItemBinding
import com.example.xhackdev.domain.models.Network

class NetworkDiffCallback(
    private val oldList: List<Network>,
    private val newList: List<Network>
): DiffUtil.Callback(){

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNetwork = oldList[oldItemPosition]
        val newNetwork = newList[newItemPosition]

        return oldNetwork == newNetwork
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRequest = oldList[oldItemPosition]
        val newRequest = newList[newItemPosition]

        return oldRequest.contact.equals(newRequest.contact, false)
    }
}

class NetworksAdapter: RecyclerView.Adapter<NetworksAdapter.NetworkViewHolder>() {

    var itemSource: List<Network> = emptyList()
        set(newValue) {
            val diffCallback = NetworkDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemCount() = itemSource.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = NetworkItemBinding.inflate(inflater, parent, false)
        return NetworkViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NetworkViewHolder, position: Int) {
        holder.bind(itemSource[position])
    }

    class NetworkViewHolder(private val binding: NetworkItemBinding): RecyclerView.ViewHolder(binding.root){

        private var network: Network = Network()

        init {
            binding.network.addTextChangedListener {
                network.contact = it.toString()
            }
        }

        fun bind(network: Network){
            this.network = network
            binding.network.setText(network.contact)
        }
    }
}