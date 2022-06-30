package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentFavouritesBinding
import com.example.xhackdev.presenter.adapters.FavouritesAdapter
import com.example.xhackdev.presenter.viewModels.FavouritesViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private val binding: FragmentFavouritesBinding by viewBinding(FragmentFavouritesBinding::bind)
    private val vm: FavouritesViewModel by viewModels()
    private val tabTitles = listOf("Люди", "Хакатоны", "Команды")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBindings()
    }


    private fun setupBindings(){
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.viewPager.adapter =  FavouritesAdapter(
            this,
            arrayListOf(
                FavouritesPeopleFragment(),
                FavouritesHacksFragment(),
                FavouritesTeamsFragment()
            )
        )

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}