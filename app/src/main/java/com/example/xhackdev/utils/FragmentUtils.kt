package com.example.xhackdev.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.xhackdev.R
import com.example.xhackdev.presenter.MainActivity
import dagger.assisted.AssistedFactory


fun Fragment.mainActivity(): MainActivity{
    return requireActivity() as MainActivity
}


fun Fragment.findTopNavController(): NavController{
    val topLevelHost  = requireActivity().supportFragmentManager.findFragmentById(R.id.mainFragmentContainer) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}

