package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.data.models.UserDetailsRequestDto
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.databinding.BottomSheetRequestItemBinding
import com.example.xhackdev.domain.models.RequestItem

class BottomSheetRequestsAdapter: RecyclerView.Adapter<BottomSheetRequestsAdapter.RequestViewHolder>() {

    private var acceptRequestClickAction: (Int) -> Unit = {}
    private var declineRequestClickAction: (Int) -> Unit = {}
    private var withdrawRequestItemClickAction: (Int) -> Unit = {}

    var itemSource: List<UserDetailsRequestDto> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    fun setItemClickActions(acceptRequestClickAction: (Int) -> Unit, declineRequestClickAction: (Int) -> Unit, withdrawRequestItemClickAction: (Int) -> Unit){
        this.acceptRequestClickAction = acceptRequestClickAction
        this.declineRequestClickAction = declineRequestClickAction
        this.withdrawRequestItemClickAction = withdrawRequestItemClickAction
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itembinding = BottomSheetRequestItemBinding.inflate(inflater, parent, false)
        return RequestViewHolder(itembinding)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bind(itemSource[position])
    }

    override fun getItemCount() = itemSource.size


    class RequestViewHolder(private val binding: BottomSheetRequestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var request: UserDetailsRequestDto? = null
        private var acceptAction: (Int) -> Unit = {}
        private var declineAction: (Int) -> Unit = {}
        private var withdrawAction: (Int) -> Unit = {}

        init {
            binding.acceptBtn.setOnClickListener {
                request?.let {
                    acceptAction.invoke(it.id)
                }
            }

            binding.declineBtn.setOnClickListener {
                request?.let {
                    declineAction.invoke(it.id)
                }
            }

            binding.revokeBtn.setOnClickListener {
                request?.let {
                    withdrawAction.invoke(it.id)
                }
            }
        }

        fun bind(model: UserDetailsRequestDto) {

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
    }
}