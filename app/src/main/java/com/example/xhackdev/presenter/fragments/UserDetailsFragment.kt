package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentUserDetailsBinding
import com.example.xhackdev.presenter.viewModels.UserDetailsViewModel
import com.example.xhackdev.utils.viewModelCreator

class UserDetailsFragment: Fragment(R.layout.fragment_user_details) {

    private val bindings: FragmentUserDetailsBinding by viewBinding(FragmentUserDetailsBinding::bind)
    private val args: UserDetailsFragmentArgs by navArgs()
    private val vm: UserDetailsViewModel by viewModelCreator { UserDetailsViewModel(args.userId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindings.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


    }
}