package com.example.xhackdev.presenter.fragments

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentProfileBinding
import com.example.xhackdev.presenter.MainActivity
import com.example.xhackdev.presenter.adapters.SkillsAdapter
import com.example.xhackdev.presenter.viewModels.ProfileViewModel
import com.example.xhackdev.utils.findTopNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val bindings: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val vm: ProfileViewModel by viewModels()
    private val adapter = SkillsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBindings()

        vm.userInfo.observe(viewLifecycleOwner) {
            Glide.with(bindings.userAvatarImage)
                .load(it.avatarUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_default_user_avatar)
                .error(R.drawable.ic_default_user_avatar)
                .into(bindings.userAvatarImage)

            bindings.apply {
                userName.text = it.name
                userEmail.text = it.email
                userSpecialization.text = it.specialization
                adapter.itemSource = it.tags

                contacts.text = ""
                it.networks.forEach { network ->
                    contacts.append("${network.contact}\n")
                }
            }
        }
    }


    private fun setupBindings() {
        bindings.apply {
            val layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)

            skillsList.layoutManager = layoutManager
            skillsList.adapter = adapter
            skillsList.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.left = 9
                    outRect.right = 9
                    outRect.bottom = 9
                    outRect.top = 9
                }
            })
            editButton.setOnClickListener {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment()
                )
            }


            userName.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFavouritesGraph())
            }


            logOutBtn.setOnClickListener {
                lifecycleScope.launch {
                    vm.logOut()
                    val builder = NavOptions.Builder()
                    val options =
                        builder.setPopUpTo(findTopNavController().graph.startDestinationId, true)
                            .build()
                    findTopNavController().navigate(R.id.action_global_loginFragment, null, options)
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            vm.loadContent()
        }
    }
}