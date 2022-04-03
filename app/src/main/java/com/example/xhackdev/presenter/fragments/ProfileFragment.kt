package com.example.xhackdev.presenter.fragments

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentProfileBinding
import com.example.xhackdev.presenter.adapters.SkillsAdapter
import com.example.xhackdev.presenter.viewModels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private val bindings: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val vm: ProfileViewModel by viewModels()
    private val adapter = SkillsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)

        bindings.skillsList.layoutManager = layoutManager
        bindings.skillsList.adapter = adapter
        bindings.skillsList.addItemDecoration(object: RecyclerView.ItemDecoration(){
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left = 9
                outRect.right = 9
                outRect.bottom = 9

                if (parent.getChildLayoutPosition(view) != 0) {
                    outRect.top = 9
                } else {
                    outRect.top = 0
                }
            }
        })


        vm.items.observe(viewLifecycleOwner){
            adapter.itemSource = it
        }

        bindings.editButton.setOnClickListener {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment()
            )
        }
    }
}