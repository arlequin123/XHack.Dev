package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.databinding.FavouritesPeopleItemBinding
import com.example.xhackdev.databinding.SkillItemBinding

class FavouritesPeopleAdapter :
    RecyclerView.Adapter<FavouritesPeopleAdapter.FavouritesPeopleViewHolder>() {

    var itemSource: List<Any> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesPeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = FavouritesPeopleItemBinding.inflate(inflater, parent, false)
        return FavouritesPeopleViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavouritesPeopleViewHolder, position: Int) {
        holder.bind(itemSource[position])
    }

    override fun getItemCount() = itemSource.size


    class FavouritesPeopleViewHolder(
        private val binding: FavouritesPeopleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(requestHeader: Any) {

        }
    }
}