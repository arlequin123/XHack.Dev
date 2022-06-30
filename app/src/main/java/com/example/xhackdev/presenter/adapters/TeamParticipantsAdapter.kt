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
import com.example.xhackdev.presenter.viewHolders.ParticipantViewHolder
import com.example.xhackdev.utils.setBadgeBackgroundResource
import ru.nikartm.support.BadgePosition

class TeamParticipantsAdapter: RecyclerView.Adapter<ParticipantViewHolder>(){

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



}