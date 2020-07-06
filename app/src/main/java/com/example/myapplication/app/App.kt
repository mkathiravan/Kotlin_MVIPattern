package com.example.myapplication.app

import android.app.Application
import com.example.myapplication.app.di.apiModule
import com.example.myapplication.app.di.netModule
import com.example.myapplication.app.di.viewModelScope
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application()
{
    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin() {
        startKoin {
            androidContext(this@App)
            modules(netModule, apiModule, viewModelScope)
        }
    }
}