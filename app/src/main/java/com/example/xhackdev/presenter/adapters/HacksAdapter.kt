package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.data.models.HackDto
import com.example.xhackdev.databinding.HackItemBinding
import com.example.xhackdev.domain.models.Hack
import com.example.xhackdev.domain.models.RequestItem
import com.example.xhackdev.presenter.viewHolders.HackViewHolder

class HacksAdapter : PagingDataAdapter<HackDto, HackViewHolder>(HacksDiffCallBack()) {

    private var itemClickAction: (Int) -> Unit = {}

    fun setItemClickAction(action: (Int) -> Unit) {
        itemClickAction = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = HackItemBinding.inflate(inflater, parent, false)
        return HackViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HackViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.setOnClickAction(itemClickAction)
    }
}

class HacksDiffCallBack : DiffUtil.ItemCallback<HackDto>() {
    override fun areItemsTheSame(oldItem: HackDto, newItem: HackDto): Boolean {
        return oldItem.description == newItem.description
    }

    override fun areContentsTheSame(oldItem: HackDto, newItem: HackDto): Boolean {
        return oldItem == newItem
    }
}