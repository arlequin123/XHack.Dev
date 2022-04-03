package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentTeamDetailsBinding
import com.example.xhackdev.presenter.viewModels.TeamDetailsViewModel
import com.example.xhackdev.utils.ViewModelFactory
import com.example.xhackdev.utils.viewModelCreator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailsFragment : Fragment(R.layout.fragment_team_details) {

    private val bindings: FragmentTeamDetailsBinding by viewBinding(FragmentTeamDetailsBinding::bind)
    private val args: TeamDetailsFragmentArgs by navArgs()
    private val vm: TeamDetailsViewModel by viewModelCreator { TeamDetailsViewModel(args.teamId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindings.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}