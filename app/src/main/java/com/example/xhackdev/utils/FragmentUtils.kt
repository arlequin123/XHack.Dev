package com.example.xhackdev.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xhackdev.presenter.MainActivity

typealias ViewModelCreator = () -> ViewModel

fun Fragment.mainActivity(): MainActivity{
    return requireActivity() as MainActivity
}


class ViewModelFactory(private val viewModelCreator: ViewModelCreator) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelCreator() as T
    }
}

//todo research
inline fun <reified VM : ViewModel> Fragment.viewModelCreator(noinline creator: ViewModelCreator) : Lazy<VM> {
    return viewModels { ViewModelFactory(creator) }
}