package com.example.instatexnopos.ui.profil.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instatexnopos.data.Resource
import com.example.instatexnopos.data.halpers.ProfileHelper
import com.example.instatexnopos.data.model.User

class EditProfileViewModel(private val  profileHelper: ProfileHelper):ViewModel() {
    private var mutableUser:MutableLiveData<Resource<User>> = MutableLiveData()
    val user:LiveData<Resource<User>>get() = mutableUser
    fun getCurrentUser(){
        mutableUser.value = Resource.loading()
        profileHelper.getProfileData(
            {
                mutableUser.value = Resource.success(it)
            },
            {
                mutableUser.value = Resource.error(it)
            })
    }
    private var mutableProfileEdit:MutableLiveData<Resource<String>> = MutableLiveData()
    val profileEdit:LiveData<Resource<String>>
    get() = mutableProfileEdit
    fun editProfile(user:User){
        mutableProfileEdit.value = Resource.loading()
        profileHelper.editProfile(user,
            {
                mutableProfileEdit.value = Resource.success("success")
            },
            {
                mutableProfileEdit.value = Resource.error(it)
            })
    }
}