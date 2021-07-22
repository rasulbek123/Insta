package com.example.instatexnopos.ui.profil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instatexnopos.data.halpers.AuthHalper
import com.example.instatexnopos.data.Resource
import com.example.instatexnopos.data.halpers.ProfileHelper
import com.example.instatexnopos.data.model.User

class ProfileViewModel(private val profileHalper: ProfileHelper):ViewModel() {
    private var mutableProfile:MutableLiveData<Resource<User>> = MutableLiveData()
    val profile:LiveData<Resource<User>>
    get() = mutableProfile
    fun getProfileData(){
        mutableProfile.value = Resource.loading()
        profileHalper.getProfileData(
            {
                mutableProfile.value = Resource.success(it)
            },
            {
                mutableProfile.value = Resource.error(it)
            }
        )
    }
}