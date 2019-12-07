package com.luisenricke.hackchiapas.ui.main

import android.os.Bundle
import com.luisenricke.hackchiapas.R
import com.luisenricke.hackchiapas.common.BaseActivity

class MainActivity : BaseActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
