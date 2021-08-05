package com.example.instatexnopos.ui.profil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instatexnopos.data.halpers.AuthHalper
import com.example.instatexnopos.data.Resource
import com.example.instatexnopos.data.halpers.PostHelper
import com.example.instatexnopos.data.halpers.ProfileHelper
import com.example.instatexnopos.data.model.Post
import com.example.instatexnopos.data.model.User

class ProfileViewModel(private val profileHalper: ProfileHelper,private val postHelper: PostHelper):ViewModel() {
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
    private var mutablePosts:MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    val posts:LiveData<Resource<List<Post>>> get() = mutablePosts
    fun getCurrentUsersPosts(){
        mutablePosts.value = Resource.loading()
        postHelper.getCurrentUsersPosts(
            {
                mutablePosts.value = Resource.success(it)
            },
            {
                mutablePosts.value =  Resource.error(it)
            }
        )
    }
}