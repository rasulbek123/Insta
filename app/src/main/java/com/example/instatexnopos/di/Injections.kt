package com.example.instatexnopos.di

import com.example.instatexnopos.data.FirebaseHelper
import com.example.instatexnopos.ui.auth.signin.SingInViewModel
import com.example.instatexnopos.ui.auth.signup.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirebaseHelper(get(),get()) }
}
val viewModelModule = module {
    viewModel{SignUpViewModel(get())}
    viewModel{SingInViewModel(get())}
}