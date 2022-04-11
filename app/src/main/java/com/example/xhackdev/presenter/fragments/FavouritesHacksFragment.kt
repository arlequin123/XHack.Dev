package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentFavouritesHacksBinding
import com.example.xhackdev.presenter.adapters.HacksAdapter

class FavouritesHacksFragment: Fragment(R.layout.fragment_favourites_hacks) {

    private val binding: FragmentFavouritesHacksBinding by viewBinding(
        FragmentFavouritesHacksBinding::bind)
    private val adapter = HacksAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.hacksRecyclerView.layoutManager = layoutManager
        binding.hacksRecyclerView.adapter = adapter


        adapter.itemsSource = listOf(1,2,3,4,5,6)
    }
}