package com.example.xhackdev.presenter.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentEditProfileBinding
import com.example.xhackdev.domain.models.Network
import com.example.xhackdev.presenter.adapters.NetworksAdapter
import com.example.xhackdev.presenter.adapters.SkillsAdapter
import com.example.xhackdev.presenter.viewModels.EditProfileViewModel
import com.example.xhackdev.utils.ImagePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val bindings: FragmentEditProfileBinding by viewBinding(FragmentEditProfileBinding::bind)
    private val vm: EditProfileViewModel by viewModels()
    private val skillsAdapter = SkillsAdapter()
    private val networksAdapter = NetworksAdapter()
    private var networks = ArrayList<Network>()
    private var tagsIds = emptyList<Int>()

    private lateinit var imagePicker: ImagePicker


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagePicker = ImagePicker(requireActivity().activityResultRegistry, viewLifecycleOwner) {
            Glide.with(bindings.userAvatarImage)
                .load(it)
                .circleCrop()
                .placeholder(R.drawable.ic_default_user_avatar)
                .error(R.drawable.ic_default_user_avatar)
                .into(bindings.userAvatarImage)
        }

        val layoutManager =
            GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)

        bindings.skillsList.layoutManager = layoutManager
        bindings.skillsList.adapter = skillsAdapter
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
                outRect.top = 9
            }
        })

        val networkLayoutManager = LinearLayoutManager(requireContext())
        bindings.contactsList.layoutManager = networkLayoutManager
        bindings.contactsList.adapter = networksAdapter

        bindings.contactsList.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.bottom = 10
                outRect.top = 10
            }
        })

        vm.userInfo.observe(viewLifecycleOwner) {
            if (!it.avatarUrl.isNullOrBlank()) {
                Glide.with(bindings.userAvatarImage)
                    .load(it.avatarUrl)
                    .circleCrop()
                    .placeholder(R.drawable.ic_default_user_avatar)
                    .error(R.drawable.ic_default_user_avatar)
                    .into(bindings.userAvatarImage)
            } else {
                bindings.userAvatarImage.setImageResource(R.drawable.ic_default_user_avatar)
            }

            bindings.nameEditText.setText(it.name)
            bindings.specializationEditText.setText(it.specialization)
            skillsAdapter.itemSource = it.tags
            networks = it.networks
            networksAdapter.itemSource = networks

            tagsIds = it.tags.map { tag -> tag.id }
        }

        bindings.newContactBtn.setOnClickListener {
            if(networks.any { it.contact.isBlank() }) return@setOnClickListener

            networks = ArrayList(networks).apply { add(Network()) }
            networksAdapter.itemSource = networks
        }

        bindings.pickAvatarImage.setOnClickListener {
            imagePicker.pickImage()
        }

        bindings.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        bindings.editSkillsButton.setOnClickListener {
            findNavController().navigate(EditProfileFragmentDirections.actionEditProfileFragmentToSkillsFragment(tagsIds.toIntArray()))
        }
    }


}