package com.example.xhackdev.presenter.fragments

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.xhackdev.R
import com.example.xhackdev.data.models.ShortTeamDetailsDto
import com.example.xhackdev.data.models.UserDetailsRequestDto
import com.example.xhackdev.databinding.FragmentUserDetailsBinding
import com.example.xhackdev.databinding.UserDetailsBottomSheetBinding
import com.example.xhackdev.presenter.adapters.BottomSheetRequestsAdapter
import com.example.xhackdev.presenter.adapters.ChooseTeamBottomSheetAdapter
import com.example.xhackdev.presenter.adapters.RequestActionDelegate
import com.example.xhackdev.presenter.adapters.SkillsAdapter
import com.example.xhackdev.presenter.viewModels.UserDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailsFragment: Fragment(R.layout.fragment_user_details) {

    private val bindings: FragmentUserDetailsBinding by viewBinding(FragmentUserDetailsBinding::bind)
    private val args: UserDetailsFragmentArgs by navArgs()
    @Inject lateinit var assistedFactory: UserDetailsViewModel.Factory
    private val vm: UserDetailsViewModel by viewModels { UserDetailsViewModel.provideFactory(assistedFactory, args.userId) }
    private var requests: List<UserDetailsRequestDto> = emptyList()
    private var teams: List<ShortTeamDetailsDto> = emptyList()


    private val adapter = SkillsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindings.sendRequestButton.setOnClickListener {
            if(requests.isNotEmpty()) {
                showBottomDialog()
            } else {
                vm.getMyteams()
            }
        }

        bindings.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        bindings.favoritesButton.setOnClickListener {
            vm.addOrRemoveFavourites()
        }

        vm.isBookmarked.observe(viewLifecycleOwner){
            bindings.favoritesButton.setBackgroundResource(if(it) R.drawable.ic_star else R.drawable.ic_star_unchecked)
        }

        vm.myteams.observe(viewLifecycleOwner){
            teams = it
            sendRequest()
        }

        vm.userInfo.observe(viewLifecycleOwner){
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

            //adapter.itemSource = it.tags

            it.networks.forEach { network ->
                bindings.contacts.append("$network\n")
            }

            requests = it.requests

            bindings.sendRequestButton.text = if(requests.isNullOrEmpty()) "Send request" else "Show requests"
        }
    }

    fun sendRequest(){
        if(teams.isEmpty()){
            Toast.makeText(requireContext(), "Net command loh", Toast.LENGTH_SHORT).show()
            return
        }

        if(teams.size == 1){
            vm.sendRequestToUser(teams.first().id)
            return
        }

        showBottomTeamsDialog()
    }

    //TODO refactor this shit
    private fun showBottomDialog(){
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val bottomSheetBinding: UserDetailsBottomSheetBinding = UserDetailsBottomSheetBinding.inflate(layoutInflater, null, false)
        val adapter = BottomSheetRequestsAdapter()

        bottomSheetBinding.closeBtn.setOnClickListener {
            dialog.dismiss()
        }

        bottomSheetBinding.title.text = "Запросы"

        val layoutManager = LinearLayoutManager(requireContext())
        bottomSheetBinding.requestsRv.layoutManager = layoutManager

        bottomSheetBinding.requestsRv.adapter = adapter
        adapter.submitList(requests)

        adapter.setDelegate(object: RequestActionDelegate{
            override fun acceptRequest(requestId: Int) {
                vm.acceptRequestFromUser(requestId)
            }

            override fun declineRequest(requestId: Int) {
                vm.declineRequestFromUser(requestId)
                requests = requests.filter { r -> r.id != requestId }
                adapter.submitList(requests)

                if(requests.isEmpty()){
                    bindings.sendRequestButton.text = "Send request"
                    dialog.dismiss()
                }
            }

            override fun withdrawRequest(requestId: Int) {
                vm.withdrowRequestToUser(requestId)
            }

        })

        dialog.setContentView(bottomSheetBinding.root)
        dialog.show()
    }

    //TODO refactor this shit
    private fun showBottomTeamsDialog(){
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val bottomSheetBinding: UserDetailsBottomSheetBinding = UserDetailsBottomSheetBinding.inflate(layoutInflater, null, false)
        val adapter = ChooseTeamBottomSheetAdapter()

        bottomSheetBinding.closeBtn.setOnClickListener {
            dialog.dismiss()
        }

        bottomSheetBinding.title.text = "Команды"

        val layoutManager = LinearLayoutManager(requireContext())
        bottomSheetBinding.requestsRv.layoutManager = layoutManager

        bottomSheetBinding.requestsRv.adapter = adapter

        adapter.itemSource = teams

        adapter.setItemClickAction { vm.sendRequestToUser(it) }

        dialog.setContentView(bottomSheetBinding.root)
        dialog.show()


    }
}