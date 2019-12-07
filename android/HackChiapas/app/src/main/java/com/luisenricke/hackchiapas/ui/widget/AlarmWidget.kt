package com.luisenricke.hackchiapas.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.RemoteViews
import com.luisenricke.hackchiapas.Constants.CLICK_ACTIVE_WIDGET
import com.luisenricke.hackchiapas.Constants.CLICK_WIDGET
import com.luisenricke.hackchiapas.R
import com.luisenricke.hackchiapas.utils.PreferenceHelper
import timber.log.Timber
import java.text.SimpleDateFormat


/**
 * Implementation of App Widget functionality.
 */
class AlarmWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context,
                appWidgetManager,
                appWidgetId
            )
        }
    }

    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
        updateAppWidget(context!!, appWidgetManager!!, appWidgetId)
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val intent = Intent(context, ClickIntentService::class.java)
            intent.action = ClickIntentService.ACTION_CLICK
            val pendingIntent =
                PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val remoteViews = RemoteViews(context.packageName, R.layout.alarm_widget)

            remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)

            val click: Boolean? = PreferenceHelper.get(context, CLICK_ACTIVE_WIDGET, Boolean::class)
            remoteViews.setTextViewText(R.id.appwidget_text, "Help")
/*
            if (click == true) {
                PreferenceHelper.set(applicationContext, CLICK_ACTIVE_WIDGET, false)
            } else {
                remoteViews.setTextViewText(R.id.appwidget_text, "Apagado")
            }*/
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }
}