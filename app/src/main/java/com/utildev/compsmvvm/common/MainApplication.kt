package com.utildev.compsmvvm.common

import android.app.Application
import com.utildev.compsmvvm.di.networkModule
import com.utildev.compsmvvm.di.repositoryModule
import com.utildev.compsmvvm.di.sharedPrefModule
import com.utildev.compsmvvm.di.viewModelModule
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