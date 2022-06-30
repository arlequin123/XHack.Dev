package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.databinding.SkillItemBinding
import com.example.xhackdev.domain.models.Tag
import com.example.xhackdev.presenter.viewHolders.SkillViewHolder

class SkillsAdapter : RecyclerView.Adapter<SkillViewHolder>() {

    var itemSource: List<Tag> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = SkillItemBinding.inflate(inflater, parent, false)
        return SkillViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        holder.bind(itemSource[position])
    }

    override fun getItemCount() = itemSource.size



}