package com.luisenricke.hackchiapas.ui.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.luisenricke.hackchiapas.R


object Toolbar {
    fun configBack(
        activity: AppCompatActivity,
        inflater: View
    ): MaterialToolbar? {
        //val appBarLayout: AppBarLayout = inflater.findViewById(R.id.appBar)
        //appBarLayout.outlineProvider = null // Disable shadows
        val toolbar: MaterialToolbar = inflater.findViewById(R.id.toolbar)
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }
        return toolbar
    }
}