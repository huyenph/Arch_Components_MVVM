package com.arch.compsmvvm.common

import android.app.Application
import com.arch.compsmvvm.di.networkModule
import com.arch.compsmvvm.di.repositoryModule
import com.arch.compsmvvm.di.sharedPrefModule
import com.arch.compsmvvm.di.viewModelModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            networkModule,
            repositoryModule,
            sharedPrefModule,
            viewModelModule
        ))
    }
}