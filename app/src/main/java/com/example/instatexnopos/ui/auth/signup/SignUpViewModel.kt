package com.example.instatexnopos.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instatexnopos.data.FirebaseHelper
import com.example.instatexnopos.data.Resource

class SignUpViewModel(private val firebaseHelper: FirebaseHelper):ViewModel() {
    private var mutableSignUpStatus:MutableLiveData<Resource<String?>> = MutableLiveData()
     val  signUpStatus:LiveData<Resource<String?>>
    get() = mutableSignUpStatus
    fun signUp(email:String,password:String){
    mutableSignUpStatus.value = Resource.loading()
        firebaseHelper.signUp(email,password,
            {
                addUserToDb()

            },
            {
                mutableSignUpStatus.value = Resource.error(it)
            }
            )
    }
    private fun addUserToDb(){
        firebaseHelper.addUserToDb(
            {
                mutableSignUpStatus.value = Resource.success(null)
            },
            {
                mutableSignUpStatus.value = Resource.error(it)
            }
        )
    }
}