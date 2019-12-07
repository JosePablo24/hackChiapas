package com.luisenricke.hackchiapas.common

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.luisenricke.hackchiapas.data.AppDatabase

@Suppress("unused")
abstract class BaseActivity : AppCompatActivity() {

    lateinit var database: AppDatabase
    private lateinit var inputMethodManager: InputMethodManager
    private var viewsType: MutableList<Class<out TextView>> = mutableListOf(TextView::class.java,MaterialTextView::class.java,MaterialButton::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = AppDatabase.getInstance(this)
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val actual = currentFocus
            if (actual is TextInputEditText) {
                val borderView = Rect()
                actual.getGlobalVisibleRect(borderView)
                if (!borderView.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    inputMethodManager.hideSoftInputFromWindow(actual.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}