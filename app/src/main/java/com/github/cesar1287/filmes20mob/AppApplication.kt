package com.github.cesar1287.filmes20mob

import android.app.Application
import com.github.cesar1287.filmes20mob.di.AppComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(AppComponent.getAllModules())
        }
    }
}