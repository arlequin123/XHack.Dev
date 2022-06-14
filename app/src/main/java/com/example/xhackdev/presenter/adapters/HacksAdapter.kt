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

class HacksAdapter: PagingDataAdapter<HackDto, HacksAdapter.HackViewHolder>(HacksDiffCallBack()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = HackItemBinding.inflate(inflater, parent, false)
        return HackViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HackViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }



    class HackViewHolder(private val binding: HackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hack: HackDto) {
            binding.hackName.text = hack.name
            binding.description.text = hack.description
            Glide.with(binding.hackAvatarImage)
                .load(hack.avatarUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_default_team_avatar)
                .error(R.drawable.ic_default_team_avatar)
                .into(binding.hackAvatarImage)
        }
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