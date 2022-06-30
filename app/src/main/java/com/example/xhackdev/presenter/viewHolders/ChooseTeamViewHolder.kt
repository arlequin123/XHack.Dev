package com.example.xhackdev.presenter.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.data.models.ShortTeamDetailsDto
import com.example.xhackdev.databinding.ChooseTeamRequestIemBinding

class ChooseTeamViewHolder(private val binding: ChooseTeamRequestIemBinding): RecyclerView.ViewHolder(binding.root){

    private var team: ShortTeamDetailsDto? = null
    private var onClickAction: (Int) -> Unit = {}

    init {
        binding.root.setOnClickListener {
            team?.let {
                onClickAction.invoke(it.id)
            }
        }
    }

    fun bind(model: ShortTeamDetailsDto){
        team = model
        if(!model.avatarUrl.isNullOrEmpty()){
            Glide.with(binding.avatarImage)
                .load(model.avatarUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_default_team_avatar)
                .error(R.drawable.ic_default_team_avatar)
                .into(binding.avatarImage)
        } else {
            binding.avatarImage.setImageResource(R.drawable.ic_default_team_avatar)
        }

        binding.name.text = model.name
    }

    fun setOnClickAction(action: (Int) -> Unit){
        onClickAction = action
    }
}