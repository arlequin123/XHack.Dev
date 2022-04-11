package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.databinding.RequestHeaderBinding
import com.example.xhackdev.databinding.UserRequestItemBinding
import com.example.xhackdev.domain.models.RequestItem
import com.example.xhackdev.domain.models.RequestsToTeam
import com.example.xhackdev.presenter.adapters.primitives.ItemType
import com.example.xhackdev.presenter.viewHolders.RequestHeaderViewHolder
import com.example.xhackdev.presenter.viewHolders.RequestViewHolder
import com.example.xhackdev.utils.getPositionInformation


class RequestsAdapter(

): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var userRequestItemClickAction: (RequestItem) -> Unit = {}
    private var teamRequestItemClickAction: (RequestItem) -> Unit = {}

    var itemsSource: List<RequestsToTeam> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    fun setItemClickActions(userRequestItemClickAction: (RequestItem) -> Unit, teamRequestItemClickAction: (RequestItem) -> Unit){
        this.userRequestItemClickAction = userRequestItemClickAction
        this.teamRequestItemClickAction = teamRequestItemClickAction
    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if(item is RequestsToTeam) ItemType.Section.ordinal else ItemType.Item.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = UserRequestItemBinding.inflate(inflater, parent, false)
        val headerBinding = RequestHeaderBinding.inflate(inflater, parent, false)

        if(viewType == ItemType.Section.ordinal) return RequestHeaderViewHolder(headerBinding)

        return RequestViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if(holder is RequestViewHolder){
            (item as RequestItem).let {
                holder.bind(item)
                val action = when (item.type){
                    RequestType.TeamToUser -> teamRequestItemClickAction
                    RequestType.UserToTeam -> userRequestItemClickAction
                    else -> { {} }
                }
                holder.setOnClickAction(action)
                return
            }
        }

        if(holder is RequestHeaderViewHolder){
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