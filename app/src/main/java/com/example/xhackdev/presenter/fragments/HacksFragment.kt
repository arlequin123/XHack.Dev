package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentHacksBinding
import com.example.xhackdev.presenter.adapters.HacksAdapter
import com.example.xhackdev.presenter.adapters.LoaderStateAdapter
import com.example.xhackdev.presenter.viewModels.HacksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HacksFragment: Fragment(R.layout.fragment_hacks) {

    private val bindings: FragmentHacksBinding by viewBinding(FragmentHacksBinding::bind)
    private val vm: HacksViewModel by viewModels()
    private val adapter = HacksAdapter()
    private val loadAdapter = LoaderStateAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        bindings.hacksList.layoutManager = layoutManager
        bindings.hacksList.adapter = adapter.withLoadStateFooter(loadAdapter)

        vm.isRefreshing.observe(viewLifecycleOwner){
            bindings.swipeRefresh.isRefreshing
        }

        bindings.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
            bindings.swipeRefresh.isRefreshing = false
        }

        viewLifecycleOwner.lifecycleScope.launch{
            vm.hacks.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}