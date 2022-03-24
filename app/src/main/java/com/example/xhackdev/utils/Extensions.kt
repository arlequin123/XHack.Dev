package com.example.xhackdev.utils

import androidx.fragment.app.Fragment
import com.example.xhackdev.presenter.MainActivity

fun Fragment.mainActivity(): MainActivity{
    return requireActivity() as MainActivity
}