package com.example.xhackdev.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xhackdev.databinding.LoaderViewBinding

class LoaderStateAdapter: LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LoaderViewBinding.inflate(inflater, parent, false)
        return LoaderViewHolder(binding)
    }


    class LoaderViewHolder(private val bindings: LoaderViewBinding) :
        RecyclerView.ViewHolder(bindings.root) {
        fun bind(loadState: LoadState) {
            bindings.progressView.visibility =
                if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
        }
    }
}