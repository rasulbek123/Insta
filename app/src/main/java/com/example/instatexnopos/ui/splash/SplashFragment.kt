package com.example.instatexnopos.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.instatexnopos.R
import com.example.instatexnopos.data.Settings
import com.example.instatexnopos.databinding.FragmentSplashBinding

class SplashFragment:Fragment(R.layout.fragment_splash) {
    private lateinit var binding:FragmentSplashBinding
    val settings = Settings(requireContext())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().actionBar?.hide()
        binding = FragmentSplashBinding.bind(view)
        binding.lottieView.setMaxProgress(0.6f)
        binding.lottieView.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                if(settings.signIn){

                }else{

                }
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })
    }
}