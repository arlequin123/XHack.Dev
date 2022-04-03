package com.example.xhackdev.presenter.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.databinding.OutgoingRequestHeaderBinding
import com.example.xhackdev.domain.models.RequestsToTeam

class OutgoingRequestHeaderViewHolder(
    private val binding: OutgoingRequestHeaderBinding
    ): RecyclerView.ViewHolder(binding.root){

    fun bind(requestHeader: RequestsToTeam) {
        binding.headerTitle.text = when (requestHeader.requestType) {
            RequestType.UserToTeam -> "Запросы командам"
            RequestType.TeamToUser -> "Запросы пользователям "
            else -> String()
        }
    }
}