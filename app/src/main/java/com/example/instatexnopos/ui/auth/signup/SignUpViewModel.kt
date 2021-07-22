package com.example.instatexnopos.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instatexnopos.data.halpers.AuthHalper
import com.example.instatexnopos.data.Resource

class SignUpViewModel(private val authHalper: AuthHalper):ViewModel() {
    private var mutableSignUpStatus:MutableLiveData<Resource<String?>> = MutableLiveData()
     val  signUpStatus:LiveData<Resource<String?>>
    get() = mutableSignUpStatus
    fun signUp(email:String,password:String){
    mutableSignUpStatus.value = Resource.loading()
        authHalper.signUp(email,password,
            {
                addUserToDb()

            },
            {
                mutableSignUpStatus.value = Resource.error(it)
            }
            )
    }
    private fun addUserToDb(){
        authHalper.addUserToDb(
            {
                mutableSignUpStatus.value = Resource.success(null)
            },
            {
                mutableSignUpStatus.value = Resource.error(it)
            }
        )
    }
}