package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentChatDetailsBinding
import com.example.xhackdev.presenter.viewModels.ChatDetailsViewModel
import com.example.xhackdev.utils.viewModelCreator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatDetailsFragment: Fragment(R.layout.fragment_chat_details) {

    private val binding: FragmentChatDetailsBinding by viewBinding(FragmentChatDetailsBinding::bind)
    private val args: ChatDetailsFragmentArgs by navArgs()
    private val vm: ChatDetailsViewModel by viewModelCreator { ChatDetailsViewModel(args.chatId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}