package com.example.instatexnopos.data.halpers

import com.example.instatexnopos.data.N
import com.example.instatexnopos.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthHalper(private val auth:FirebaseAuth, private val db:FirebaseFirestore) {

    fun signUp(
        email:String,
        password:String,
        onSuccess:()->Unit,
        onFailure:(msg:String?)->Unit){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
    fun signIn(
        email:String,
        password:String,
        onSucces:()->Unit,
        onFailure:(mgs:String)->Unit){
        auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                onSucces.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
    fun addUserToDb(onSucces: () -> Unit,onFailure: (mgs: String) -> Unit){
        val user = User(auth.currentUser!!.uid, auth.currentUser!!.email!!)
        db.collection(N.USERS).document(user.uid).set(user)
            .addOnSuccessListener {
                onSucces.invoke()
            }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
}