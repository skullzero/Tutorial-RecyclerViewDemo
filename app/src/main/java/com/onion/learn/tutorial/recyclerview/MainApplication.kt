package com.onion.learn.tutorial.recyclerview

import android.app.Application
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        setupDependencyInjection()
    }

    private fun setupDependencyInjection() {
        startKoin {
            modules(recyclerviewModule)
        }
    }
}