package com.example.instatexnopos

import android.content.Context
import android.content.SharedPreferences

class Settings(context: Context) {
    companion object{
        const val KEY_SIGNED_IN = "keySignIn"
    }
    private val prefs = context.getSharedPreferences("${context.packageName}.pref",Context.MODE_PRIVATE)
    var signIn:Boolean
    set(value) = prefs.edit().putBoolean(KEY_SIGNED_IN,value).apply()
    get() = prefs.getBoolean(KEY_SIGNED_IN,false)

}