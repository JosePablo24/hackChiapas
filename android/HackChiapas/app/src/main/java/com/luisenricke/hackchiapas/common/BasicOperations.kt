package com.luisenricke.hackchiapas.common

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

object BasicOperations {
    fun toast(activity: AppCompatActivity, message: String, isShort: Boolean = true) {
        if (isShort) {
            Toast.makeText(activity.baseContext, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity.baseContext, message, Toast.LENGTH_LONG).show()
        }
    }
}