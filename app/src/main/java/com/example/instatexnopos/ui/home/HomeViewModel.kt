package com.example.instatexnopos.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instatexnopos.data.Resource
import com.example.instatexnopos.data.halpers.PostHelper
import com.example.instatexnopos.data.model.Post

class HomeViewModel(private val postHelper: PostHelper ):ViewModel() {
    private var mutableHomePost:MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    val homePost: LiveData<Resource<List<Post>>> get() = mutableHomePost
    fun getUsersPosts(){
        mutableHomePost.value = Resource.loading()
        postHelper.getUsersPosts(
            {
                mutableHomePost.value = Resource.success(it)
            },
            {
                mutableHomePost.value  = Resource.error(it)
            }
        )
    }
}