package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentLoginBinding
import com.example.xhackdev.presenter.viewModels.LoginViewModel
import com.example.xhackdev.utils.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private val vm: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = ProgressBar(requireContext())
        progressBar.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        vm.isLoading.observe(viewLifecycleOwner){

        }

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

            if(!isEmailValid){
                Toast.makeText(requireContext(), "Емейл введен не по формату", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                Toast.makeText(requireContext(), "Пароль не должны быть пустыми!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            vm.tryLogin(email, password)
        }

        binding.registrationBtn.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
                    .setEmail(binding.emailEditText.text.toString())
                    .setPassword(binding.passwordEditText.text.toString())
            )
        }

        lifecycleScope.launchWhenStarted  {
            vm.sf.collect {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTabsFragment())
            }
        }
    }
}