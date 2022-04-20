package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.data.models.ShortTeamDetailsDto
import com.example.xhackdev.databinding.ChooseTeamRequestIemBinding

class ChooseTeamBottomSheetAdapter: RecyclerView.Adapter<ChooseTeamBottomSheetAdapter.ChooseTeamViewHolder>() {

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
}