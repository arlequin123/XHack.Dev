package com.example.xhackdev.presenter.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.databinding.SkillItemBinding
import com.example.xhackdev.domain.models.Tag

class SkillViewHolder(
    private val binding: SkillItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tag: Tag) {
        binding.label.text = tag.name
    }
}