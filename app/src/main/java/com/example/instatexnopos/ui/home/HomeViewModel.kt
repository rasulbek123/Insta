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
        postHelper.getAllPosts(
            {
                mutableHomePost.value = Resource.success(it)
            },
            {
                mutableHomePost.value  = Resource.error(it)
            }
        )
    }
    private var mutableLike:MutableLiveData<Resource<Int>> = MutableLiveData()
    val like:LiveData<Resource<Int>> get() = mutableLike
    fun onDoubleClicked(post: Post) {
        mutableLike.value = Resource.loading()
        postHelper.onDoubleClicked(post,
            {
                mutableLike.value = Resource.success(it)
            },
            {
                mutableLike.value = Resource.error(it)
            })
    }
}