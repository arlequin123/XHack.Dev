package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.databinding.SelectionItemBinding
import com.example.xhackdev.domain.models.Tag
import com.example.xhackdev.presenter.viewHolders.TagSelectionItemViewHolder


class ItemDiffCallback : DiffUtil.ItemCallback<Tag>(){
    override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem == newItem
    }
}

class TagListAdapter: ListAdapter<Tag, TagSelectionItemViewHolder>(ItemDiffCallback())  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagSelectionItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = SelectionItemBinding.inflate(inflater, parent, false)
        return TagSelectionItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TagSelectionItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



}

