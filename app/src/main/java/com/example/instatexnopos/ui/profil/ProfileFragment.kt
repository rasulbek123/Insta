package com.example.instatexnopos.ui.profil

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.instatexnopos.R
import com.example.instatexnopos.data.ResourceState
import com.example.instatexnopos.databinding.ProfilFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment:Fragment(R.layout.profil_fragment) {
    private lateinit var binding: ProfilFragmentBinding
    private val viewModel:ProfileViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        binding = ProfilFragmentBinding.bind(view)
        viewModel.getProfileData()
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