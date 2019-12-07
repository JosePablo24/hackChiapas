package com.luisenricke.hackchiapas.ui.widget

import android.app.IntentService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import com.luisenricke.hackchiapas.Constants.CLICK_ACTIVE_WIDGET
import com.luisenricke.hackchiapas.Constants.CLICK_DOBLE_VALIDATION
import com.luisenricke.hackchiapas.utils.PreferenceHelper
import timber.log.Timber


class ClickIntentService : IntentService("ClickIntentService") {
    var mMilliseconds: Long = 5000

    var mCountDownTimer: CountDownTimer = object : CountDownTimer(mMilliseconds, 1000) {
        override fun onFinish() {
            if (PreferenceHelper.get(
                    applicationContext,
                    CLICK_ACTIVE_WIDGET,
                    Boolean::class
                ) == true
            ) {
                val v =
                    getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
// Vibrate for 500 milliseconds
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
                } else { //deprecated in API 26
                    v.vibrate(5000)
                }
                Toast.makeText(applicationContext, "Se termino el tiempo", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "Se paro el tiempo", Toast.LENGTH_SHORT).show()
            }
            Timber.i("End time")
            PreferenceHelper.set(applicationContext, CLICK_ACTIVE_WIDGET, false)
            PreferenceHelper.delete(applicationContext, CLICK_DOBLE_VALIDATION)
        }


        override fun onTick(millisUntilFinished: Long) {
            if (PreferenceHelper.get(
                    applicationContext,
                    CLICK_ACTIVE_WIDGET,
                    Boolean::class
                ) == true
            )
                Timber.i("time: $millisUntilFinished")
        }
    }


    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val action = intent.action
            if (ACTION_CLICK == action) {
                handleClick()
            }
        }
    }

    private fun handleClick() {
        var click = PreferenceHelper.get(this, CLICK_ACTIVE_WIDGET, Boolean::class)
        var doubleClick: Boolean? = null
        Timber.i("Widget active: $click")

        if (click == false) { // Encender el widget
            Timber.i("Start time")
            mCountDownTimer.start()
            PreferenceHelper.set(this, CLICK_ACTIVE_WIDGET, true)
            PreferenceHelper.set(this, CLICK_DOBLE_VALIDATION, true)
        } else {
            mCountDownTimer.cancel()
            PreferenceHelper.set(this, CLICK_ACTIVE_WIDGET, false)
            PreferenceHelper.delete(this, CLICK_DOBLE_VALIDATION)
        }

        val appWidgetManager = AppWidgetManager.getInstance(this)
        val widgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(
                this,
                AlarmWidget::class.java
            )
        )

        for (appWidgetId in widgetIds) {
            AlarmWidget.updateAppWidget(applicationContext, appWidgetManager, appWidgetId)
        }
    }

    companion object {
        const val ACTION_CLICK = "com.luisenricke.hackchiapas.widget.click"
    }
}