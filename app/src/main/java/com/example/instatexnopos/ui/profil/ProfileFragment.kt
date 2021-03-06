package com.example.instatexnopos.ui.profil

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.instatexnopos.R
import com.example.instatexnopos.data.ResourceState
import com.example.instatexnopos.databinding.ProfilFragmentBinding
import com.example.instatexnopos.ui.MainFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment:Fragment(R.layout.profil_fragment) {
    private lateinit var binding: ProfilFragmentBinding
    private val viewModel:ProfileViewModel by viewModel()
    private val adapter = ProfilePostAdapter()
    private lateinit var parentNavController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProfilFragmentBinding.bind(view)
        setUpObservers()
        binding.rvPost.adapter = adapter
        viewModel.getProfileData()
        viewModel.getCurrentUsersPosts()
        parentNavController = (parentFragment?.parentFragment as MainFragment).findNavController()
        binding.btnEdit.setOnClickListener {
            parentNavController.navigate(R.id.action_mainFragment_to_editProfleFragment)
        }
    }
    private fun setUpObservers(){
        viewModel.profile.observe(viewLifecycleOwner,{
            when(it.status){
                ResourceState.LOADING ->{
                    setLoading(true)
                }
                ResourceState.SUCCESS->{
                    setLoading(false)
                    binding.apply {
                        val u  = it.data!!
                        tvUsername.text = u.email
                        tvName.text = u.name
                        tvBiography.text = u.biography
                        tvFollowersCount.text = u.followersCount.toString()
                        tvFollowingCount.text = u.followingCount.toString()
                        tvPostCount.text = u.postCount.toString()
                        Glide.with(this@ProfileFragment)
                            .load(u.image)
                            .centerCrop()
                            .placeholder(R.drawable.ic_baseline_person_24)
                            .into(avatar)
                    }
                }
            }
        })
        viewModel.posts.observe(viewLifecycleOwner)
        {
            when(it.status){
                ResourceState.LOADING ->{
                    setLoading(true)
                }
                ResourceState.SUCCESS->{
                    setLoading(false)
                    adapter.models = it.data!!
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
    private fun setLoading(isloading:Boolean){
       binding.apply {
           loading.isVisible = isloading
           btnEdit.isEnabled = !isloading
           rvPost.isEnabled = !isloading
           tvUsername.isEnabled = !isloading

       }
    }
}