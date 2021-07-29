package com.example.instatexnopos.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.instatexnopos.R
import com.example.instatexnopos.data.ResourceState
import com.example.instatexnopos.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment:Fragment(R.layout.fragment_home) {
 private lateinit var binding:FragmentHomeBinding
 private val viewModel:HomeViewModel by viewModel()
    private val adapter=HomePostAdapter()
 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     binding = FragmentHomeBinding.bind(view)
     setUpObservers()
     binding.rvPostHome.adapter = adapter
     viewModel.getUsersPosts()
    }
    private fun setUpObservers(){
        viewModel.homePost.observe(viewLifecycleOwner, {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    adapter.models = it.data!!
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    fun setLoading(isLoading:Boolean){
        binding.apply {
            loading.isVisible = isLoading
            rvPostHome.isEnabled = !isLoading
        }
    }
}