package com.example.instatexnopos

import android.app.Application
import com.example.instatexnopos.di.adapterModule
import com.example.instatexnopos.di.dataModule
import com.example.instatexnopos.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        val modules = listOf(dataModule, viewModelModule)
        startKoin { // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@App)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            koin.loadModules(modules)
        }
    }
}