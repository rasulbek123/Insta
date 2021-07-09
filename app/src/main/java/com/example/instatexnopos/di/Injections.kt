package com.example.instatexnopos.di

import com.example.instatexnopos.data.FirebaseHelper
import com.example.instatexnopos.ui.auth.signup.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseHelper(get()) }
}
val viewModelModule = module {
    viewModel{SignUpViewModel(get())}
}