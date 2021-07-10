package com.example.instatexnopos.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.instatexnopos.R
import com.example.instatexnopos.data.Settings
import com.example.instatexnopos.databinding.FragmentSplashBinding

class SplashFragment:Fragment(R.layout.fragment_splash) {
    private lateinit var binding:FragmentSplashBinding
    val settings = Settings(requireContext())
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().actionBar?.hide()
        binding = FragmentSplashBinding.bind(view)
        navController = Navigation.findNavController(view)
        binding.lottieView.setMaxProgress(0.6f)
        binding.lottieView.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                if(settings.signIn){
                    navController.navigate(R.id.action_splashFragment_to_mainFragment)
                }else{
                    navController.navigate(R.id.action_splashFragment_to_signInFragment)
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })
    }
}