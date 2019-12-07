package com.luisenricke.hackchiapas

import android.app.Application
import com.luisenricke.hackchiapas.Constants.CLICK_ACTIVE_WIDGET
import com.luisenricke.hackchiapas.Constants.CLICK_WIDGET
import com.luisenricke.hackchiapas.data.AppDatabase
import com.luisenricke.hackchiapas.utils.PreferenceHelper
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        if (PreferenceHelper.get(this, CLICK_ACTIVE_WIDGET, Boolean::class) == null)
            PreferenceHelper.set(this, CLICK_ACTIVE_WIDGET, false)

        AppDatabase.getInstance(this)
    }
}