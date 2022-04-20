package com.example.xhackdev.presenter.adapters

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.databinding.TeamParticipantItemBinding
import com.example.xhackdev.domain.models.ShortUserDetailsModel
import com.example.xhackdev.utils.setBadgeBackgroundResource
import ru.nikartm.support.BadgePosition

class TeamParticipantsAdapter: RecyclerView.Adapter<TeamParticipantsAdapter.ParticipantViewHolder>(){

    private var itemClickAction: (Int) -> Unit = {}

    var itemSource: List<ShortUserDetailsModel> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    fun setItemClickAction(action: (Int) -> Unit){
        itemClickAction = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = TeamParticipantItemBinding.inflate(inflater, parent, false)
        return ParticipantViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        holder.bind(itemSource[position])
    }

    override fun getItemCount() = itemSource.size


    class ParticipantViewHolder(
        private val binding: TeamParticipantItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ShortUserDetailsModel) {
            binding.name.text = model.name

            if(!model.avatarUrl.isNullOrEmpty()){
                Glide.with(binding.avatarImage)
                    .load(model.avatarUrl)
                    .circleCrop()
                    .placeholder(R.drawable.ic_default_user_avatar)
                    .error(R.drawable.ic_default_user_avatar)
                    .into(binding.avatarImage)
            } else {
                binding.avatarImage.setImageResource(R.drawable.ic_default_user_avatar)
            }

            if(model.isCaptain) {
                binding.avatarImage.visibleBadge(true)
                    .setFixedBadgeRadius(25f).setBadgeColor(Color.GREEN)
                    .setBadgePosition(BadgePosition.TOP_RIGHT)
                binding.avatarImage.setBadgeBackgroundResource(R.drawable.ic_crown)
            }
        }
    }
}