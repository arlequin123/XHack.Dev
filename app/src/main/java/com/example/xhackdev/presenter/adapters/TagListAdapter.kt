package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.databinding.SelectionItemBinding
import com.example.xhackdev.domain.models.Tag


class ItemDiffCallback : DiffUtil.ItemCallback<Tag>(){
    override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem == newItem
    }
}

class TagListAdapter: ListAdapter<Tag, TagListAdapter.SelectionItemViewHolder>(ItemDiffCallback())  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectionItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = SelectionItemBinding.inflate(inflater, parent, false)
        return SelectionItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SelectionItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class SelectionItemViewHolder(private val binding: SelectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tag: Tag) {
            binding.title.text = tag.name
            binding.switchBtn.isSelected = tag.isSelected
        }
    }
}

