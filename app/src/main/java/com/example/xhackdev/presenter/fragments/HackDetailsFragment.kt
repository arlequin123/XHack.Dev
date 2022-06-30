package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentHackDetailsBinding
import com.example.xhackdev.presenter.viewModels.HackDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class HackDetailsFragment : Fragment(R.layout.fragment_hack_details) {

    private val bindings: FragmentHackDetailsBinding by viewBinding(FragmentHackDetailsBinding::bind)
    private val arg: HackDetailsFragmentArgs by navArgs()
    @Inject
    lateinit var factory: HackDetailsViewModel.Factory
    private val vm: HackDetailsViewModel by viewModels {
        HackDetailsViewModel.provideFactory(
            factory,
            arg.hackId
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBindings()
        initSubscribes()
    }


    private fun initSubscribes() {
        vm.isBookmarked.observe(viewLifecycleOwner) {
            bindings.favoritesButton.setBackgroundResource(if (it) R.drawable.ic_star else R.drawable.ic_star_unchecked)
        }

        vm.hack.observe(viewLifecycleOwner) {
            Glide.with(bindings.hackAvatarImage)
                .load(it.avatarUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_default_team_avatar)
                .error(R.drawable.ic_default_team_avatar)
                .into(bindings.hackAvatarImage)


            bindings.hackName.text = it.name
            val formattedStartDate = SimpleDateFormat("dd.MM.yyyy").format(it.startDate)
            val formattedEndDate = SimpleDateFormat("dd.MM.yyyy").format(it.endDate)
            val date = "$formattedStartDate - $formattedEndDate"
            bindings.dateTv.text = date
            bindings.registrationTv.text = it.siteUrl
            bindings.placement.text = it.location
        }
    }


    private fun setupBindings() {
        bindings.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        bindings.favoritesButton.setOnClickListener {
            vm.addOrRemoveFavourites()
        }
    }
}