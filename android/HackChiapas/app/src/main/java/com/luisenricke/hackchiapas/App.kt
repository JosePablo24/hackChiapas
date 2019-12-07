package com.luisenricke.hackchiapas

import android.app.Application
import com.luisenricke.hackchiapas.data.AppDatabase
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        AppDatabase.getInstance(this)
    }
}