package com.luisenricke.hackchiapas.ui.widget

import android.app.IntentService
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.telephony.SmsManager
import android.widget.RemoteViews
import android.widget.Toast
import com.luisenricke.hackchiapas.Constants.CLICK_ACTIVE_WIDGET
import com.luisenricke.hackchiapas.Constants.CLICK_DOBLE_VALIDATION
import com.luisenricke.hackchiapas.R
import com.luisenricke.hackchiapas.common.extension.format
import com.luisenricke.hackchiapas.ui.main.MainActivity
import com.luisenricke.hackchiapas.utils.LocationTrack
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
                var longitude: Double = 0.0
                var latitude: Double = 0.0
                // Latitud - Longitud
                val locationTrack: LocationTrack = LocationTrack(applicationContext)
                if (locationTrack.canGetLocation()) {
                    longitude = locationTrack.getLongitude()
                    latitude = locationTrack.getLatitude()
                    Timber.i("$latitude,$longitude")

                    // SMS
                    val intent = Intent()
                    val pi = PendingIntent.getActivity(applicationContext, 0, intent, 0)
                    val sms: SmsManager = SmsManager.getDefault()
                    sms.sendTextMessage(
                        "9612594528",
                        null,
                        "Marcame, estoy en posible peligro.",
                        pi,
                        null
                    )
                    sms.sendTextMessage(
                        "9612594528",
                        null,
                        "Ubicación: http://maps.google.com/?q=${latitude.format(6)},${longitude.format(
                            6
                        )}",
                        pi,
                        null
                    )
                    // Vibrador
                    val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(
                            VibrationEffect.createOneShot(
                                500,
                                VibrationEffect.DEFAULT_AMPLITUDE
                            )
                        )
                    } else { //deprecated in API 26
                        v.vibrate(5000)
                    }
                    Toast.makeText(applicationContext, "Se termino el tiempo", Toast.LENGTH_SHORT).show()
                } else {
                    locationTrack.showSettingsAlert()
                }

            } else {
                /*
                val remoteViews = RemoteViews(applicationContext.packageName, R.layout.alarm_widget)
                if (PreferenceHelper.get(
                        applicationContext,
                        CLICK_ACTIVE_WIDGET,
                        Boolean::class
                    ) == false
                ) {
                    remoteViews.setInt(
                        R.id.appwidget_text,
                        "setBackgroundResource",
                        android.R.color.holo_red_light
                    )
                } else {
                    remoteViews.setInt(
                        R.id.appwidget_text,
                        "setBackgroundResource",
                        R.color.colorPrimary
                    )
                }*/
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
        Timber.i("Widget active: $click")
        //val remoteViews = RemoteViews(applicationContext.packageName, R.layout.alarm_widget)


        if (click == false) { // Encender el widget
            Timber.i("Start time")
            //remoteViews.setInt(R.id.appwidget_text, "setBackgroundResource", android.R.color.holo_red_light)
            mCountDownTimer.start()
            PreferenceHelper.set(this, CLICK_ACTIVE_WIDGET, true)
            PreferenceHelper.set(this, CLICK_DOBLE_VALIDATION, true)
        } else {
            //remoteViews.setInt(R.id.appwidget_text, "setBackgroundResource", R.color.colorPrimary)
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