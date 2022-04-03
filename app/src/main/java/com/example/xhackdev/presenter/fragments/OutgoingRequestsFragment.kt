package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentOutgoingRequestsBinding
import com.example.xhackdev.presenter.adapters.OutgoingRequestsAdapter
import com.example.xhackdev.presenter.viewModels.OutgoingRequestsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OutgoingRequestsFragment: Fragment(R.layout.fragment_outgoing_requests) {

    private val bindings: FragmentOutgoingRequestsBinding by viewBinding(FragmentOutgoingRequestsBinding::bind)
    private val vm: OutgoingRequestsViewModel by viewModels()
    private val adapter = OutgoingRequestsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        bindings.recyclerView.layoutManager = layoutManager
        bindings.recyclerView.adapter = adapter

        vm.isRefreshing.observe(viewLifecycleOwner){
            bindings.swipeRefresh.isRefreshing = it
        }

        bindings.swipeRefresh.setOnRefreshListener {
            vm.refreshContent()
        }

        bindings.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        vm.requests.observe(viewLifecycleOwner){
            adapter.itemsSource = it
        }
    }
}