package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentMessengerBinding
import com.example.xhackdev.presenter.adapters.ChatListAdapter
import com.example.xhackdev.presenter.viewModels.MessengerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessengerFragment: Fragment(R.layout.fragment_messenger) {

    private val bindings: FragmentMessengerBinding by viewBinding(FragmentMessengerBinding::bind)
    private val vm: MessengerViewModel by viewModels()
    private val adapter = ChatListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        bindings.chatRecyclerView.layoutManager = layoutManager
        bindings.chatRecyclerView.adapter = adapter

        adapter.setItemClickActions {
            findNavController().navigate(MessengerFragmentDirections.actionMessengerFragmentToChatDetailsFragment(5))
        }
    }
}