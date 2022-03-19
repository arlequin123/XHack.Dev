package com.example.xhackdev.utils

import androidx.fragment.app.Fragment
import com.example.xhackdev.presenter.MainActivity

fun Fragment.mainActivty(): MainActivity{
    return requireActivity() as MainActivity
}