package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.databinding.HackItemBinding
import com.example.xhackdev.databinding.TeamItemBinding
import com.example.xhackdev.presenter.viewHolders.TeamViewHolder

class TeamsAdapter: RecyclerView.Adapter<TeamViewHolder>() {

    var itemsSource: List<Any> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = TeamItemBinding.inflate(inflater, parent, false)
        return TeamViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(itemsSource[position])
    }

    override fun getItemCount() = itemsSource.size

}