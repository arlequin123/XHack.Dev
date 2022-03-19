package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentHomeBinding
import com.example.xhackdev.presenter.adapters.RequestsAdapter
import com.example.xhackdev.presenter.viewModels.HomeViewModel
import com.example.xhackdev.utils.mainActivty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {

    private val bindings: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private val vm: HomeViewModel by viewModels()
    private val adapter = RequestsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        bindings.recyclerView.layoutManager = layoutManager
        bindings.recyclerView.adapter = adapter

        mainActivty().showLoader(true)

        vm.users.observe(viewLifecycleOwner){
            adapter.setData(it)
        }
    }
}