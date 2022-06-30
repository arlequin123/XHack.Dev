package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
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
class SkillsFragment : Fragment(R.layout.fragment_skills) {

    private val bindings: FragmentSkillsBinding by viewBinding(FragmentSkillsBinding::bind)
    private val adapter = TagListAdapter()
    @Inject
    lateinit var assistedFactory: SkillsViewModel.Factory
    private val vm: SkillsViewModel by viewModels {
        SkillsViewModel.provideFactory(
            assistedFactory,
            args.selectedIds
        )
    }
    private val args: SkillsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBindings()

        vm.tagList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    private fun setupBindings() {
        bindings.apply {
            val layoutManager = LinearLayoutManager(requireContext())

            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            selectAllBtn.setOnClickListener {
                val isAllSelected = adapter.currentList.all { it.isSelected }
                adapter.currentList.forEach {
                    it.isSelected = !isAllSelected
                }
                bindings.selectAllBtn.text = "Выбрать все"
                adapter.notifyDataSetChanged()
            }

            saveBtn.setOnClickListener {
                setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(RESULT_EXTRA_KEY to vm.tagList.value?.filter { it.isSelected })
                )
                findNavController().popBackStack()
            }
        }
    }

    companion object {
        const val REQUEST_KEY = "tag_list__key"
        const val RESULT_EXTRA_KEY = "extra_key"
    }
}