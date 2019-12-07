package com.luisenricke.hackchiapas.ui.view

import android.util.Patterns
import android.view.View
import com.google.android.material.textfield.TextInputLayout


object FormView {
    fun setError(
        view: View,
        validation: Boolean,
        message: String?
    ) {
        if (view is TextInputLayout) (view as TextInputLayout)
            .error = if (validation) null else message
    }

    fun isEmail(email: String?): Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPassword(password: String): Boolean {
        return password.length >= 4
    }
}