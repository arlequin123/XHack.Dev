package com.example.xhackdev.presenter.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xhackdev.R
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

        binding.userName.text = requestsToTeam.user.name
        binding.description.text = requestsToTeam.user.description
        binding.description.visibility =
            if (requestsToTeam.user.description.isEmpty()) View.GONE else View.VISIBLE

        if (!requestsToTeam.user.avatarUrl.isNullOrBlank()) {
            Glide.with(binding.userAvatarImage)
                .load(requestsToTeam.user.avatarUrl)
                .placeholder(R.drawable.ic_default_user_avatar)
                .error(R.drawable.ic_default_user_avatar)
                .into(binding.userAvatarImage)
        } else {
            binding.userAvatarImage.setImageResource(R.drawable.ic_default_user_avatar)
        }
    }

    fun setOnClickAction(clickAction: (RequestItem) -> Unit){
        this.clickAction = clickAction
    }
}