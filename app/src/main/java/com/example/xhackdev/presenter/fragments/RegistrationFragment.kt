package com.example.xhackdev.presenter.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.xhackdev.R
import com.example.xhackdev.databinding.FragmentRegistrationBinding
import com.example.xhackdev.presenter.viewModels.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment: Fragment(R.layout.fragment_registration) {

    private val bindings: FragmentRegistrationBinding by viewBinding(FragmentRegistrationBinding::bind)
    private val vm: RegistrationViewModel by viewModels()
    private val args: RegistrationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindings.emailEditText.setText(args.email)
        bindings.passwordEditText.setText(args.password)

        bindings.registernBtn.setOnClickListener {
            val email = bindings.emailEditText.text.toString()
            val password = bindings.passwordEditText.text.toString()
            val name = bindings.nameEditText.text.toString()
            if(email.isEmpty() || password.isEmpty() || name.isEmpty()){
                Toast.makeText(requireContext(), "Емейл/пароль/имя не должны быть пустыми!", Toast.LENGTH_SHORT).show()
            } else{
                vm.tryRegister(email, password, name)
            }
        }

        bindings.loginBtn.setOnClickListener {
            findNavController().popBackStack()
        }


    }
}