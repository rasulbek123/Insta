package com.example.instatexnopos.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instatexnopos.data.FirebaseHelper
import com.example.instatexnopos.data.Resource

class SingInViewModel(private val firebaseHelper: FirebaseHelper) : ViewModel() {
    private var mutableSinInStatus: MutableLiveData<Resource<String?>> = MutableLiveData()
    val signInStatus: LiveData<Resource<String?>>
        get() = mutableSinInStatus

    fun signIn(email: String, password: String) {
        mutableSinInStatus.value = Resource.loading()
        firebaseHelper.signIn(email, password,
            {
                mutableSinInStatus.value = Resource.success(null)
            },
            {
                mutableSinInStatus.value = Resource.error(it)
            }
        )
    }
}