package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentHacksBinding
import com.example.xhackdev.presenter.adapters.HacksAdapter
import com.example.xhackdev.presenter.viewModels.HacksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HacksFragment: Fragment(R.layout.fragment_hacks) {

    private val bindings: FragmentHacksBinding by viewBinding(FragmentHacksBinding::bind)
    private val vm: HacksViewModel by viewModels()
    private val adapter = HacksAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        bindings.hacksList.layoutManager = layoutManager
        bindings.hacksList.adapter = adapter

        vm.isRefreshing.observe(viewLifecycleOwner){
            bindings.swipeRefresh.isRefreshing = it
        }

        bindings.swipeRefresh.setOnRefreshListener {
            vm.refreshContent()
        }

        vm.requests.observe(viewLifecycleOwner){
            adapter.itemsSource = it
        }
    }
}