package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentSkillsBinding
import com.example.xhackdev.presenter.adapters.TagListAdapter
import com.example.xhackdev.presenter.viewModels.SkillsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SkillsFragment: Fragment(R.layout.fragment_skills) {

    private val bindings: FragmentSkillsBinding by viewBinding(FragmentSkillsBinding::bind)
    private val adapter = TagListAdapter()
    @Inject lateinit var assistedFactory: SkillsViewModel.Factory
    private val vm : SkillsViewModel by viewModels { SkillsViewModel.provideFactory(assistedFactory, args.selectedIds) }
    private val args: SkillsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())

        bindings.recyclerView.layoutManager = layoutManager
        bindings.recyclerView.adapter = adapter

        bindings.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        vm.tagList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }
}