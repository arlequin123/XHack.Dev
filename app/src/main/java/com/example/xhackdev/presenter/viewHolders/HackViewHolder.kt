package com.example.xhackdev.presenter.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.data.models.HackDto
import com.example.xhackdev.databinding.HackItemBinding

class HackViewHolder(private val binding: HackItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var hack: HackDto? = null
    private var clickAction: (Int) -> Unit = {}

    init {
        binding.root.setOnClickListener {
            hack?.let { h -> clickAction(h.id) }
        }
    }

    fun bind(hack: HackDto) {
        this.hack = hack
        binding.hackName.text = hack.name
        binding.description.text = hack.description
        Glide.with(binding.hackAvatarImage)
            .load(hack.avatarUrl)
            .circleCrop()
            .placeholder(R.drawable.ic_default_team_avatar)
            .error(R.drawable.ic_default_team_avatar)
            .into(binding.hackAvatarImage)
    }

    fun setOnClickAction(clickAction: (Int) -> Unit){
        this.clickAction = clickAction
    }
}