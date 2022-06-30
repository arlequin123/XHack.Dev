package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R

import com.example.xhackdev.databinding.FragmentFavouritesTeamsBinding
import com.example.xhackdev.presenter.adapters.TeamsAdapter


class FavouritesTeamsFragment: Fragment(R.layout.fragment_favourites_teams) {

    private val binding: FragmentFavouritesTeamsBinding by viewBinding(FragmentFavouritesTeamsBinding::bind)
    private val adapter = TeamsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBindings()
    }


    private fun setupBindings(){
        val layoutManager = LinearLayoutManager(requireContext())
        binding.teamsRecyclerView.layoutManager = layoutManager
        binding.teamsRecyclerView.adapter = adapter

        adapter.itemsSource = listOf(1,2,3,4,5,6)
    }
}