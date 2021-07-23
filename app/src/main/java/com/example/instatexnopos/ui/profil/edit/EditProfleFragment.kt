package com.example.instatexnopos.ui.profil.edit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.instatexnopos.R
import com.example.instatexnopos.data.ResourceState
import com.example.instatexnopos.data.model.User
import com.example.instatexnopos.databinding.FragmentProfileEditBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfleFragment:Fragment(R.layout.fragment_profile_edit) {
    private lateinit var binding: FragmentProfileEditBinding
    private lateinit var navController: NavController
    private val viewModel:EditProfileViewModel by viewModel()
    private lateinit var cUser:User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCurrentUser()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileEditBinding.bind(view)
        navController = findNavController()
        setUpObservers()
        binding.apply {
            cancelBtn.setOnClickListener {
                navController.popBackStack()
            }
            doneBtn.setOnClickListener {
                cUser.biography = editBiography.text.toString()
                cUser.name = editName.text.toString()
                viewModel.editProfile(cUser)
            }
        }

    }
    private fun setUpObservers(){
        viewModel.profileEdit.observe(viewLifecycleOwner){
            when(it.status){
                ResourceState.LOADING->{
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    Toast.makeText(requireContext(), "Your profile successfully updated", Toast.LENGTH_SHORT).show()
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.user.observe(viewLifecycleOwner)
        {
            when(it.status){
                ResourceState.LOADING->{
                    setLoading(true)
                }
                ResourceState.SUCCESS->{
                    setLoading(false)
                    cUser = it.data!!
                    binding.apply {
                        Glide.with(this@EditProfleFragment)
                            .load(cUser.image)
                            .into(userImage)
                        editName.setText(cUser.name)
                        editBiography.setText(cUser.biography)
                    }
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun setLoading(isLoading:Boolean){
        binding.apply {
            loading.isVisible = isLoading
            cancelBtn.isEnabled = !isLoading
            doneBtn.isEnabled = !isLoading
            tvEditImage.isEnabled = !isLoading
            userImage.isEnabled = !isLoading
            editName.isEnabled = !isLoading
            editBiography.isEnabled = !isLoading
        }
    }
}