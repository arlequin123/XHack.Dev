package com.example.xhackdev.presenter.fragments

import android.animation.LayoutTransition
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentTeamDetailsBinding
import com.example.xhackdev.presenter.adapters.SkillsAdapter
import com.example.xhackdev.presenter.adapters.TeamParticipantsAdapter
import com.example.xhackdev.presenter.viewModels.TeamDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TeamDetailsFragment : Fragment(R.layout.fragment_team_details) {

    private val bindings: FragmentTeamDetailsBinding by viewBinding(FragmentTeamDetailsBinding::bind)
    private val args: TeamDetailsFragmentArgs by navArgs()
    @Inject lateinit var factory: TeamDetailsViewModel.Factory
    private val vm: TeamDetailsViewModel by viewModels { TeamDetailsViewModel.provideFactory(factory, args.teamId) }

    private val skillsAdapter = SkillsAdapter()
    private val teamParticipantsAdapter = TeamParticipantsAdapter()

    private var isCollapsed = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindings.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        bindings.favoritesButton.setOnClickListener {
            vm.addOrRemoveFavourites()
        }

        vm.isBookmarked.observe(viewLifecycleOwner){
            bindings.favoritesButton.setBackgroundResource(if(it) R.drawable.ic_star else R.drawable.ic_star_unchecked)
        }

        vm.teamInfo.observe(viewLifecycleOwner){

            if(!it.avatarUrl.isNullOrBlank()){
                Glide.with(bindings.teamAvatarImage)
                    .load(it.avatarUrl)
                    .circleCrop()
                    .placeholder(R.drawable.ic_default_team_avatar)
                    .error(R.drawable.ic_default_team_avatar)
                    .into(bindings.teamAvatarImage)
            } else {
                bindings.teamAvatarImage.setImageResource(R.drawable.ic_default_team_avatar)
            }

            bindings.teamName.text = it.name
            bindings.description.text = it.description
            bindings.description.maxLines = 3
            bindings.descriptionBtn.setOnClickListener {
                if(isCollapsed){
                    bindings.description.maxLines = Integer.MAX_VALUE
                    bindings.descriptionBtn.text = "Collapse"
                }else{
                    bindings.description.maxLines = 3

                    bindings.descriptionBtn.text = "Show all"
                }
                isCollapsed = !isCollapsed
            }

            applyTransition()

            //bindings.descriptionBtn.visibility =


            bindings.teamDescriptionLayout.visibility = if(it.description.isEmpty()) View.GONE else View.VISIBLE

            val layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)

            bindings.participantList.layoutManager = layoutManager
            bindings.participantList.adapter = teamParticipantsAdapter
            bindings.participantList.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.left = 20
                    outRect.right = 20
                    outRect.bottom = 15

                }
            })

            teamParticipantsAdapter.itemSource = it.members
        }
    }

    private fun applyTransition(){
        val transition = LayoutTransition()
        transition.setDuration(200)
        transition.enableTransitionType(LayoutTransition.CHANGING)
        bindings.teamDescriptionLayout.layoutTransition = transition
    }
}