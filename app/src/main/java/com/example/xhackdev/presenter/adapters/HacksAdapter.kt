package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.databinding.HackItemBinding

class HacksAdapter: RecyclerView.Adapter<HacksAdapter.HackViewHolder>() {

    var itemsSource: List<Any> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = HackItemBinding.inflate(inflater, parent, false)
        return HackViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HackViewHolder, position: Int) {
        holder.bind(itemsSource[position])
    }

    override fun getItemCount() = itemsSource.size


    class HackViewHolder(private val binding: HackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hack: Any) {

        }
    }
}