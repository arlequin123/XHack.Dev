package com.example.xhackdev.presenter.fragments

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentUserDetailsBinding
import com.example.xhackdev.presenter.adapters.SkillsAdapter
import com.example.xhackdev.presenter.viewModels.UserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailsFragment: Fragment(R.layout.fragment_user_details) {

    private val bindings: FragmentUserDetailsBinding by viewBinding(FragmentUserDetailsBinding::bind)
    private val args: UserDetailsFragmentArgs by navArgs()
    @Inject lateinit var assistedFactory: UserDetailsViewModel.Factory
    private val vm: UserDetailsViewModel by viewModels { UserDetailsViewModel.provideFactory(assistedFactory, args.userId) }

    private val adapter = SkillsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindings.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        vm.userInfo.observe(viewLifecycleOwner){
            if (!it.avatarUrl.isNullOrBlank()) {
                Glide.with(bindings.userAvatarImage)
                    .load(it.avatarUrl)
                    .placeholder(R.drawable.ic_default_user_avatar)
                    .error(R.drawable.ic_default_user_avatar)
                    .into(bindings.userAvatarImage)
            } else {
                bindings.userAvatarImage.setImageResource(R.drawable.ic_default_user_avatar)
            }

            bindings.userName.text = it.name
            bindings.userSpecialization.text = it.specialization
            bindings.userEmail.text = it.email


            val layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)

            bindings.skillsList.layoutManager = layoutManager
            bindings.skillsList.adapter = adapter
            bindings.skillsList.addItemDecoration(object : RecyclerView.ItemDecoration() {
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

            adapter.itemSource = it.tags

            it.networks.forEach { network ->
                bindings.contacts.append("$network\n")
            }
        }
    }
}