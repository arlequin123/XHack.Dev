package com.example.xhackdev.presenter.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.databinding.UserRequestItemBinding
import com.example.xhackdev.domain.models.RequestItem

class RequestViewHolder(
    private val binding: UserRequestItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var requestItem: RequestItem? = null
    private var clickAction: (RequestItem) -> Unit = {}

    init {
        binding.root.setOnClickListener {
            requestItem?.let(clickAction)
        }
    }

    fun bind(requestsToTeam: RequestItem) {
        requestItem = requestsToTeam

        val avatarUrl: String? = when (requestsToTeam.type){
            RequestType.UserToTeam -> {
                binding.userName.text = requestsToTeam.user.name
                requestsToTeam.user.avatarUrl
            }
            RequestType.TeamToUser -> {
                binding.userName.text = requestsToTeam.team.name
                requestsToTeam.team.avatarUrl
            }
            else -> null
        }

        if (!avatarUrl.isNullOrBlank()) {
            Glide.with(binding.userAvatarImage)
                .load(avatarUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_default_user_avatar)
                .error(R.drawable.ic_default_user_avatar)
                .into(binding.userAvatarImage)
        } else {
            binding.userAvatarImage.setImageResource(R.drawable.ic_default_user_avatar)
        }

        binding.description.text = requestsToTeam.user.description
        binding.description.visibility =
            if (requestsToTeam.user.description.isEmpty()) View.GONE else View.VISIBLE


    }

    fun setOnClickAction(clickAction: (RequestItem) -> Unit){
        this.clickAction = clickAction
    }
}