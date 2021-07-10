package com.example.instatexnopos.ui.auth.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.instatexnopos.R
import com.example.instatexnopos.data.ResourceState
import com.example.instatexnopos.databinding.FragmentSignInBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment:Fragment(R.layout.fragment_sign_in) {
    private lateinit var binding:FragmentSignInBinding
    private val viewModel:SingInViewModel by viewModel()
    private lateinit var  navCantroller:NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        navCantroller=Navigation.findNavController(view)
        binding.apply {
            btnsignIn.setOnClickListener {
                var success = true
                if(etEmail.text.isNullOrEmpty()){
                    etEmail.error = getString(R.string.fill_the_field)
                    success = false
                }
                if(etPassword.text.isNullOrEmpty()){
                    etPassword.error = getString(R.string.fill_the_field)
                    success = false
                }
                if(!success) return@setOnClickListener
                else{
                    viewModel.signIn(etEmail.text.toString(),etPassword.text.toString())
                }
                signUp.setOnClickListener {
                    navCantroller.navigate(R.id.action_signInFragment_to_signUpFragment)
                }
            }
        }
        setObservers()

    }
    private fun setObservers (){
        viewModel.signInStatus.observe(viewLifecycleOwner,
            {
                when(it.status){
                    ResourceState.LOADING ->{
                        isEnabled(true)
                    }
                    ResourceState.SUCCESS->{
                        navCantroller.navigate(R.id.action_signInFragment_to_mainFragment)
                    }
                    ResourceState.ERROR->{
                        isEnabled(false)
                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }
    private fun isEnabled(isloading:Boolean){
        binding.apply {
            loading.isVisible = isloading
            etEmail.isEnabled = !isloading
            etPassword.isEnabled = !isloading
        }
    }
}