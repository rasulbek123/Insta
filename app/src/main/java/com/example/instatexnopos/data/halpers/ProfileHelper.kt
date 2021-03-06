package com.example.instatexnopos.data.halpers

import com.example.instatexnopos.data.N
import com.example.instatexnopos.data.model.Post
import com.example.instatexnopos.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*

class ProfileHelper(private val auth:FirebaseAuth,private val db:FirebaseFirestore) {
    fun getProfileData(onSuccess:(user:User)->Unit,onFailure:(msg:String?)->Unit){
        db.collection(N.USERS).document(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val res = it.toObject(User::class.java)
                res?.let { user->
                    onSuccess.invoke(user)
                }?: kotlin.run { onFailure.invoke("User data is empty") }
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
    fun editProfile(user:User,onSuccess: () -> Unit,onFailure: (msg: String?) -> Unit){
        db.document("${N.USERS}/${user.uid}").set(user)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

}