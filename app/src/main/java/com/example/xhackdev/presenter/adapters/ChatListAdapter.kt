package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.databinding.ChatItemBinding

class ChatListAdapter : RecyclerView.Adapter<ChatListAdapter.ChatViewHolder>() {

    private var chatItemClickAction: () -> Unit = {}

    var itemsSource: List<Any> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    fun setItemClickActions(chatItemClickAction: () -> Unit){
        this.chatItemClickAction = chatItemClickAction
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ChatItemBinding.inflate(inflater, parent, false)
        return  ChatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(itemsSource[position])
        holder.setOnClickAction(chatItemClickAction)
    }

    override fun getItemCount() = itemsSource.size


    class ChatViewHolder(private val bindings: ChatItemBinding) : RecyclerView.ViewHolder(bindings.root){

        fun bind(item: Any){

        }

        fun setOnClickAction(clickAction: () -> Unit) {
            bindings.root.setOnClickListener {
                clickAction.invoke()
            }
        }
    }
}