package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentEditProfileBinding
import com.example.xhackdev.presenter.viewModels.EditProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val bindings: FragmentEditProfileBinding by viewBinding(FragmentEditProfileBinding::bind)
    private val vm: EditProfileViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindings.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}