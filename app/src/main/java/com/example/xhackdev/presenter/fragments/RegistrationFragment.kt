package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentRegistrationBinding
import com.example.xhackdev.presenter.viewModels.RegistrationViewModel
import com.example.xhackdev.utils.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val bindings: FragmentRegistrationBinding by viewBinding(FragmentRegistrationBinding::bind)
    private val vm: RegistrationViewModel by viewModels()
    private val args: RegistrationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBindings()

        lifecycleScope.launchWhenStarted {
            vm.sf.collect {
                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToTabsFragment())
            }
        }
    }

    private fun setupBindings() {
        bindings.apply {
            emailEditText.setText(args.email)
            passwordEditText.setText(args.password)

            registernBtn.setOnClickListener {
                val email = bindings.emailEditText.text.toString()
                val password = bindings.passwordEditText.text.toString()
                val name = bindings.nameEditText.text.toString()
                val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

                if (!isEmailValid) {
                    Toast.makeText(
                        requireContext(),
                        "Емейл введен не по формату",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                if (password.isEmpty() || name.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Пароль/имя не должны быть пустыми!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    vm.tryRegister(email, password, name)
                }
            }

            loginBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}