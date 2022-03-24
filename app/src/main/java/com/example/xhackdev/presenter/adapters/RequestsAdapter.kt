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
import com.example.xhackdev.utils.getPositionInformation


class RequestsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _itemsSource: List<RequestsToTeam> = emptyList()

    fun setData(requests: List<RequestsToTeam>){
        _itemsSource = requests
        notifyDataSetChanged()
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
            holder.bind(item as RequestItem)
            return
        }

        if(holder is RequestHeaderViewHolder){
            holder.bind(item as RequestsToTeam)
            return
        }
    }


    override fun getItemCount(): Int {
        var count = 0
        _itemsSource.forEach {
            count++
            count += it.size
        }

        return count
    }


    private fun getItem(position: Int): Any{
        if(_itemsSource.count() == 0) return Any()

        val positionInfo = _itemsSource.getPositionInformation(position)

        if(positionInfo.groupIndex == -1) return Any()

        val group = _itemsSource[positionInfo.groupIndex]

        if(positionInfo.isGroup) return group

        return group.get(positionInfo.itemIndexInGroup)
    }


    class RequestViewHolder(
        private val binding: UserRequestItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(requestsToTeam: RequestItem){
            binding.userName.text = requestsToTeam.user.name
            binding.description.text = requestsToTeam.user.description
            binding.description.visibility = if(requestsToTeam.user.description.isEmpty()) View.GONE else View.VISIBLE

            if(!requestsToTeam.user.avatarUrl.isNullOrBlank()){
                Glide.with(binding.userAvatarImage)
                    .load(requestsToTeam.user.avatarUrl)
                    .placeholder(R.drawable.ic_default_user_avatar)
                    .error(R.drawable.ic_default_user_avatar)
                    .into(binding.userAvatarImage)
            }else{
                binding.userAvatarImage.setImageResource(R.drawable.ic_default_user_avatar)
            }
        }
    }

    class RequestHeaderViewHolder(
        private val binding: RequestHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(requestHeader: RequestsToTeam){
            binding.headerDescription.text = when (requestHeader.requestType) {
                RequestType.UserToTeam -> "от людей"
                RequestType.TeamToUser -> "от команд"
                else -> String()
            }
            binding.headerCount.text = requestHeader.size.toString()
        }
    }


    private enum class ItemType{
        Section,
        Item
    }
}