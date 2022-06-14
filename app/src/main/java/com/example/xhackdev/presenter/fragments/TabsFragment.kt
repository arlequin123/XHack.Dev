package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentTabsBinding
import com.example.xhackdev.utils.mainActivity

class TabsFragment: Fragment(R.layout.fragment_tabs) {

    private val bindings: FragmentTabsBinding by viewBinding(FragmentTabsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController

        mainActivity().bottomNavigationView = bindings.bottomNavigationView
        NavigationUI.setupWithNavController(bindings.bottomNavigationView, navController)
    }
}