package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.data.models.UserDetailsRequestDto
import com.example.xhackdev.data.primitives.RequestType
import com.example.xhackdev.databinding.BottomSheetRequestItemBinding
import com.example.xhackdev.presenter.viewHolders.BottomSheetRequestViewHolder

class RequestDiffCallback : DiffUtil.ItemCallback<UserDetailsRequestDto>(){

    override fun areItemsTheSame(
        oldItem: UserDetailsRequestDto,
        newItem: UserDetailsRequestDto
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UserDetailsRequestDto,
        newItem: UserDetailsRequestDto
    ): Boolean {
        return oldItem == newItem
    }
}

interface RequestActionDelegate{
    fun acceptRequest(requestId: Int)
    fun declineRequest(requestId: Int)
    fun withdrawRequest(requestId: Int)
}

class BottomSheetRequestsAdapter: ListAdapter<UserDetailsRequestDto, BottomSheetRequestViewHolder>(RequestDiffCallback()) {

    private var requestDelegate: RequestActionDelegate? = null


    fun setDelegate(delegate: RequestActionDelegate){
        requestDelegate = delegate
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetRequestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itembinding = BottomSheetRequestItemBinding.inflate(inflater, parent, false)
        return BottomSheetRequestViewHolder(itembinding)
    }

    override fun onBindViewHolder(holderBottomSheet: BottomSheetRequestViewHolder, position: Int) {
        holderBottomSheet.bind(getItem(position))
        holderBottomSheet.setDelegate(requestDelegate)
    }





}