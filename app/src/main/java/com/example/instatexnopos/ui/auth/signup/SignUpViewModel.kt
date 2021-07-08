package com.example.instatexnopos.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instatexnopos.data.FirebaseHelper
import com.example.instatexnopos.data.Resource

class SignUpViewModel(private val firebaseHelper: FirebaseHelper):ViewModel() {
    var mutableSignUpStatus:MutableLiveData<Resource<String?>> = MutableLiveData()
    private val  signUpStatus:LiveData<Resource<String?>>
    get() = mutableSignUpStatus
    fun signUp(email:String,password:String){
    mutableSignUpStatus.value = Resource.loading()
        firebaseHelper.signUp(email,password,
            {
                mutableSignUpStatus.value = Resource.success(null)
            },
            {
                mutableSignUpStatus.value = Resource.error(it)
            }
            )
    }
}