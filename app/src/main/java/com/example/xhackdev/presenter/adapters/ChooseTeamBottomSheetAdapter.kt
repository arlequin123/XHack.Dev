package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.data.models.ShortTeamDetailsDto
import com.example.xhackdev.databinding.ChooseTeamRequestIemBinding
import com.example.xhackdev.presenter.viewHolders.ChooseTeamViewHolder

class ChooseTeamBottomSheetAdapter: RecyclerView.Adapter<ChooseTeamViewHolder>() {

    private var itemClickAction: (Int) -> Unit = {}

    var itemSource: List<ShortTeamDetailsDto> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }


    fun setItemClickAction(action: (Int) -> Unit){
        itemClickAction = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseTeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ChooseTeamRequestIemBinding.inflate(inflater, parent, false)
        return ChooseTeamViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ChooseTeamViewHolder, position: Int) {
        holder.bind(itemSource[position])
        holder.setOnClickAction(itemClickAction)
    }

    override fun getItemCount() = itemSource.size



}