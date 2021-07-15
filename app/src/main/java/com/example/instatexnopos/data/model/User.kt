package com.example.instatexnopos.data.model

data class User(
    val uid :String,
    val email:String,
    var name:String ="",
    var biography:String="",
    var image:String=""
)
