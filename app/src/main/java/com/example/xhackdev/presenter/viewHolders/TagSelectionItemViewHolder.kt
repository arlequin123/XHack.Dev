package com.example.xhackdev.presenter.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.databinding.SelectionItemBinding
import com.example.xhackdev.domain.models.Tag

class TagSelectionItemViewHolder(private val binding: SelectionItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var tag: Tag? = null

    init {
        binding.switchBtn.setOnCheckedChangeListener { _, isChecked ->
            tag?.isSelected = isChecked
        }
    }

    fun bind(tag: Tag) {
        this.tag = tag
        binding.title.text = tag.name
        binding.switchBtn.isChecked = tag.isSelected
    }
}