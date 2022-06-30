package com.example.xhackdev.presenter.fragments

import android.content.ContentResolver
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.data.models.TagDto
import com.example.xhackdev.databinding.FragmentEditProfileBinding
import com.example.xhackdev.domain.models.Network
import com.example.xhackdev.domain.models.Tag
import com.example.xhackdev.presenter.adapters.NetworksAdapter
import com.example.xhackdev.presenter.adapters.SkillsAdapter
import com.example.xhackdev.presenter.viewModels.EditProfileViewModel
import com.example.xhackdev.utils.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSink
import okio.source
import java.io.FileNotFoundException
import java.io.IOException


@AndroidEntryPoint
class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val bindings: FragmentEditProfileBinding by viewBinding(FragmentEditProfileBinding::bind)
    private val vm: EditProfileViewModel by viewModels()
    private val skillsAdapter = SkillsAdapter()
    private val networksAdapter = NetworksAdapter()
    private var networks = ArrayList<Network>()
    private var tags = emptyList<Tag>()

    private lateinit var imagePicker: ImagePicker


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBindings()
        initSubscribes()

        setFragmentResultListener(SkillsFragment.REQUEST_KEY) { key, bundle ->
            val tagList = bundle.get(SkillsFragment.RESULT_EXTRA_KEY) as? List<Tag>
            tagList?.let {
                vm.setTagList(it)
            }
        }
    }


    private fun initSubscribes() {
        vm.userInfo.observe(viewLifecycleOwner) {
            bindings.nameEditText.setText(it.name)
            bindings.specializationEditText.setText(it.specialization)
            skillsAdapter.itemSource = it.tags
            networks = it.networks
            networksAdapter.itemSource = networks

            tags = it.tags
        }

        vm.avatarUrl.observe(viewLifecycleOwner) {
            Glide.with(bindings.userAvatarImage)
                .load(it)
                .circleCrop()
                .placeholder(R.drawable.ic_default_user_avatar)
                .error(R.drawable.ic_default_user_avatar)
                .into(bindings.userAvatarImage)
        }

        lifecycleScope.launchWhenStarted {
            vm.sf.collect {
                findNavController().popBackStack()
            }
        }
    }


    private fun setupBindings() {
        imagePicker = ImagePicker(requireActivity().activityResultRegistry, viewLifecycleOwner) {
            it?.let { uri ->
                val stream = requireContext().contentResolver.openInputStream(uri) ?: return@let
                val requestBody = stream.readBytes().toRequestBody("image/png".toMediaTypeOrNull())
                val multiPartBody =
                    MultipartBody.Part.createFormData("image", "fileName", requestBody)
                vm.uploadImage(multiPartBody)
            }

        }

        val layoutManager =
            GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)

        bindings.apply {
            skillsList.layoutManager = layoutManager
            skillsList.adapter = skillsAdapter
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

            val networkLayoutManager = LinearLayoutManager(requireContext())
            contactsList.layoutManager = networkLayoutManager
            contactsList.adapter = networksAdapter

            contactsList.addItemDecoration(object : RecyclerView.ItemDecoration() {
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


            newContactBtn.setOnClickListener {
                if (networks.any { it.contact.isBlank() }) return@setOnClickListener

                networks = ArrayList(networks).apply { add(Network()) }
                networksAdapter.itemSource = networks
            }

            pickAvatarImage.setOnClickListener {
                imagePicker.pickImage()
            }

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            editSkillsButton.setOnClickListener {
                findNavController().navigate(
                    EditProfileFragmentDirections.actionEditProfileFragmentToSkillsFragment(
                        tags.map { it.id }.toIntArray()
                    )
                )
            }

            saveBtn.setOnClickListener {
                vm.saveContent(
                    bindings.nameEditText.text.toString(),
                    bindings.specializationEditText.text.toString(),
                    networks.filter { it.contact.isNotBlank() }.map { it.contact },
                    tags.map { TagDto(it.id, it.name) }
                )
            }
        }
    }
}

