package com.example.xhackdev.presenter.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.databinding.RequestHeaderBinding
import com.example.xhackdev.domain.models.RequestsToTeam

class RequestHeaderViewHolder(
    private val binding: RequestHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(requestHeader: RequestsToTeam) {
        binding.headerDescription.text = when (requestHeader.requestType) {
            RequestType.UserToTeam -> "от людей"
            RequestType.TeamToUser -> "от команд"
            else -> String()
        }
        binding.headerCount.text = requestHeader.size.toString()
    }
}