package com.example.instatexnopos.di

import com.example.instatexnopos.data.halpers.AuthHalper
import com.example.instatexnopos.data.halpers.PostHelper
import com.example.instatexnopos.data.halpers.ProfileHelper
import com.example.instatexnopos.ui.addpost.AddPostViewModel
import com.example.instatexnopos.ui.auth.signin.SingInViewModel
import com.example.instatexnopos.ui.auth.signup.SignUpViewModel
import com.example.instatexnopos.ui.home.HomePostAdapter
import com.example.instatexnopos.ui.home.HomeViewModel
import com.example.instatexnopos.ui.profil.ProfileViewModel
import com.example.instatexnopos.ui.profil.edit.EditProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirebaseStorage.getInstance()}
    single { AuthHalper(get(),get()) }
    single { ProfileHelper(get(),get()) }
    single { PostHelper(get(),get(),get())}
}

val viewModelModule = module {
    viewModel{SignUpViewModel(get())}
    viewModel{SingInViewModel(get())}
    viewModel{ProfileViewModel(get(),get())}
    viewModel { EditProfileViewModel(get()) }
    viewModel { AddPostViewModel(get())}
    viewModel {HomeViewModel(get())}
}