package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.databinding.OutgoingRequestHeaderBinding
import com.example.xhackdev.databinding.RequestHeaderBinding
import com.example.xhackdev.databinding.UserRequestItemBinding
import com.example.xhackdev.domain.models.RequestItem
import com.example.xhackdev.domain.models.RequestsToTeam
import com.example.xhackdev.presenter.adapters.primitives.ItemType
import com.example.xhackdev.presenter.viewHolders.OutgoingRequestHeaderViewHolder
import com.example.xhackdev.presenter.viewHolders.RequestHeaderViewHolder
import com.example.xhackdev.presenter.viewHolders.RequestViewHolder
import com.example.xhackdev.utils.getPositionInformation

class OutgoingRequestsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemsSource: List<RequestsToTeam> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if(item is RequestsToTeam) ItemType.Section.ordinal else ItemType.Item.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = UserRequestItemBinding.inflate(inflater, parent, false)
        val headerBinding = OutgoingRequestHeaderBinding.inflate(inflater, parent, false)

        if(viewType == ItemType.Section.ordinal) return OutgoingRequestHeaderViewHolder(headerBinding)

        return RequestViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        if(holder is RequestViewHolder){
            holder.bind(item as RequestItem)
            return
        }

        if(holder is OutgoingRequestHeaderViewHolder){
            holder.bind(item as RequestsToTeam)
            return
        }
    }


    override fun getItemCount(): Int {
        var count = 0
        itemsSource.forEach {
            count++
            count += it.size
        }

        return count
    }


    private fun getItem(position: Int): Any{
        if(itemsSource.count() == 0) return Any()

        val positionInfo = itemsSource.getPositionInformation(position)

        if(positionInfo.groupIndex == -1) return Any()

        val group = itemsSource[positionInfo.groupIndex]

        if(positionInfo.isGroup) return group

        return group.get(positionInfo.itemIndexInGroup)
    }
}