package com.example.instatexnopos.ui.auth.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.instatexnopos.R
import com.example.instatexnopos.databinding.FragmentSignUpBinding

class SignUpFragment:Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding:FragmentSignUpBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        binding.apply {
        btnsignUp.setOnClickListener {
            var success = true
            if (etEmail.text.isNullOrEmpty()) {
                binding.etEmail.error = "Fill th field"
                success = false
            }
            if (binding.etInputPassword.text.isNullOrEmpty()) {
                binding.etInputPassword.error = "Fill the field"
                success = false
            }
            if (binding.etConfirmPassword.text.isNullOrEmpty()) {
                binding.etInputPassword.error = "Fill the field"
                success = false
            }
            if (!success) return@setOnClickListener
            if (binding.etInputPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
                success = false
                binding.etConfirmPassword.error = "Password do not match each other"
            } else {

            }
        }
    }
    }
}