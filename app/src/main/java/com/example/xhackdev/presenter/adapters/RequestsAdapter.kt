package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.databinding.RequestHeaderBinding
import com.example.xhackdev.databinding.UserRequestItemBinding
import com.example.xhackdev.domain.interfaces.IGroup
import com.example.xhackdev.domain.models.RequestHeader
import com.example.xhackdev.domain.models.RequestToTeam
import com.example.xhackdev.utils.countGroupsAndItems
import com.example.xhackdev.utils.getPositionInformation


class RequestsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _itemsSource: List<IGroup> = emptyList()

    fun setData(users: List<IGroup>){
        _itemsSource = users
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if(item is IGroup) ItemType.Section.ordinal else ItemType.Item.ordinal
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
            //holder.bind()
            return
        }

        if(holder is RequestHeaderViewHolder){
            //.bind()
            return
        }

    }


    override fun getItemCount() = _itemsSource.countGroupsAndItems()


    private fun getItem(position: Int): Any{
        if(_itemsSource.count() == 0) return Any()

        val positionInfo = _itemsSource.getPositionInformation(position)

        if(positionInfo.groupIndex == -1) return Any()

        val group = _itemsSource[positionInfo.groupIndex]

        if(positionInfo.isGroup) return group

        val item = group[positionInfo.itemIndexInGroup]
        return item
    }


    class RequestViewHolder(
        binding: UserRequestItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(requestToTeam: RequestToTeam){

        }
    }

    class RequestHeaderViewHolder(
        binding: RequestHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(requestHeader: RequestHeader){

        }
    }


    private enum class ItemType{
        Section,
        Item
    }
}