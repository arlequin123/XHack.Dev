package com.example.xhackdev.presenter.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.data.models.UserDetailsRequestDto
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.databinding.BottomSheetRequestItemBinding
import com.example.xhackdev.presenter.adapters.RequestActionDelegate

class BottomSheetRequestViewHolder(private val binding: BottomSheetRequestItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var request: UserDetailsRequestDto? = null
    private var requestDelegate: RequestActionDelegate? = null

    init {
        binding.acceptBtn.setOnClickListener {
            request?.let {
                requestDelegate?.acceptRequest(it.id)
            }
        }

        binding.declineBtn.setOnClickListener {
            request?.let {
                requestDelegate?.declineRequest(it.id)
            }
        }

        binding.revokeBtn.setOnClickListener {
            request?.let {
                requestDelegate?.withdrawRequest(it.id)
            }
        }
    }

    fun bind(model: UserDetailsRequestDto) {
        request = model
        when(model.type){
            RequestType.UserToTeam ->{
                binding.title.text = "Входящий запрос"
                binding.description.text = "В команду: ${model.team.name}"
                binding.bntLayout.visibility = View.VISIBLE
                binding.revokeBtn.visibility = View.GONE
            }
            RequestType.TeamToUser -> {
                binding.title.text = "Исходящий запрос"
                binding.description.text = "От команды: ${model.team.name}"
                binding.bntLayout.visibility = View.GONE
                binding.revokeBtn.visibility = View.VISIBLE
            }
            else -> binding.root.visibility = View.GONE
        }
    }

    fun setDelegate(delegate: RequestActionDelegate?){
        requestDelegate = delegate
    }
}