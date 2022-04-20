package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.data.models.UserDetailsRequestDto
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.databinding.BottomSheetRequestItemBinding
import com.example.xhackdev.domain.models.RequestItem

class RequestDiffCallback(
    private val oldList: List<UserDetailsRequestDto>,
    private val newList: List<UserDetailsRequestDto>
): DiffUtil.Callback(){

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRequest = oldList[oldItemPosition]
        val newRequest = newList[newItemPosition]

        return oldRequest.id == newRequest.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRequest = oldList[oldItemPosition]
        val newRequest = newList[newItemPosition]

        return oldRequest == newRequest
    }

}

interface RequestActionDelegate{
    fun acceptRequest(requestId: Int)
    fun declineRequest(requestId: Int)
    fun withdrawRequest(requestId: Int)
}

class BottomSheetRequestsAdapter: RecyclerView.Adapter<BottomSheetRequestsAdapter.RequestViewHolder>() {

    private var requestDelegate: RequestActionDelegate? = null

    var itemSource: List<UserDetailsRequestDto> = emptyList()
        set(newValue) {
            val diffCallback = RequestDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    fun setDelegate(delegate: RequestActionDelegate){
        requestDelegate = delegate
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itembinding = BottomSheetRequestItemBinding.inflate(inflater, parent, false)
        return RequestViewHolder(itembinding)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bind(itemSource[position])
        holder.setDelegate(requestDelegate)
    }

    override fun getItemCount() = itemSource.size


    class RequestViewHolder(private val binding: BottomSheetRequestItemBinding) :
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
}