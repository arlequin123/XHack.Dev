package com.example.xhackdev.presenter.fragments

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentFavouritesPeopleBinding
import com.example.xhackdev.presenter.adapters.FavouritesPeopleAdapter

class FavouritesPeopleFragment : Fragment(R.layout.fragment_favourites_people) {

    private val binding: FragmentFavouritesPeopleBinding by viewBinding(
        FragmentFavouritesPeopleBinding::bind
    )
    private val adapter = FavouritesPeopleAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBindings()
    }


    private fun setupBindings(){
        val layoutManager =
            GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)

        binding.peopleRecyclerView.layoutManager = layoutManager
        binding.peopleRecyclerView.adapter = adapter
        binding.peopleRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left = 15
                outRect.right = 15
                outRect.bottom = 15
                outRect.top = 15
            }
        })

        adapter.itemSource = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }
}