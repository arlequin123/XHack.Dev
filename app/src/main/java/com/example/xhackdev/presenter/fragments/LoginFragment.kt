package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.ActivityMainBinding
import com.example.xhackdev.databinding.FragmentLoginBinding
import com.example.xhackdev.presenter.LoginViewModel
import com.example.xhackdev.presenter.MainActivity
import com.example.xhackdev.presenter.fragments.LoginFragmentDirections.ActionLoginFragmentToRegistrationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private val vm: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "Емейл и пароль не должны быть пустыми!", Toast.LENGTH_SHORT).show()
            } else{
                vm.tryLogin(email, password)
            }
        }

        binding.registrationBtn.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegistrationFragment(binding.emailEditText.text.toString(),
                 binding.passwordEditText.text.toString())
            )
        }

        vm.pb.subscribe {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTabsFragment())
        }
    }
}