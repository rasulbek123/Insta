package com.example.instatexnopos.ui.auth.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.instatexnopos.R
import com.example.instatexnopos.data.ResourceState
import com.example.instatexnopos.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment:Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding:FragmentSignUpBinding
    private val viewModel:SignUpViewModel by viewModel()
    private lateinit var navigationController:NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        navigationController= Navigation.findNavController(view)
        binding.apply {
        btnsignUp.setOnClickListener {
            var success = true
            if (etEmail.text.isNullOrEmpty()) {
                etEmail.error = "Fill th field"
                success = false
            }
            if (etInputPassword.text.isNullOrEmpty()) {
                etInputPassword.error = "Fill the field"
                success = false
            }
            if (binding.etConfirmPassword.text.isNullOrEmpty()) {
                etInputPassword.error = "Fill the field"
                success = false
            }
            if (!success) return@setOnClickListener
            if (etInputPassword.text.toString() != etConfirmPassword.text.toString()) {
                success = false
                etConfirmPassword.error = "Password do not match each other"
            } else {
                viewModel.signUp(etEmail.text.toString(),etInputPassword.text.toString())
            }
        }
    }
        setObservers()
    }
    private fun setObservers(){
        viewModel.signUpStatus.observe(viewLifecycleOwner, Observer {
            when(it.status){
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS ->{
                    navigationController.navigate(R.id.action_signUpFragment_to_mainFragment)
                    //action da SignUpDir shiqpay atir
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun setLoading(isLoading:Boolean){
        binding.apply {
            loading.isVisible = isLoading
            etEmail.isEnabled = !isLoading
            etInputPassword.isEnabled = !isLoading
            etConfirmPassword.isEnabled = !isLoading

        }
    }
}